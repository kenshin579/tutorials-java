package kr.pe.advenoh.spring.config;

import kr.pe.advenoh.service.JobsListener;
import kr.pe.advenoh.service.TriggersListener;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Slf4j
@Configuration
public class QuartzConfiguration {
	@Autowired
	private TriggersListener triggersListener;

	@Autowired
	private JobsListener jobsListener;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private QuartzProperties quartzProperties;

	/**
	 * Quartz 관련 설정
	 *
	 * @param applicationContext the applicationContext
	 * @return SchedulerFactoryBean
	 */
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(ApplicationContext applicationContext) {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();

		AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
		jobFactory.setApplicationContext(applicationContext);
		schedulerFactoryBean.setJobFactory(jobFactory);

		schedulerFactoryBean.setApplicationContext(applicationContext);

		Properties properties = new Properties();
		properties.putAll(quartzProperties.getProperties());

		schedulerFactoryBean.setGlobalTriggerListeners(triggersListener);
		schedulerFactoryBean.setGlobalJobListeners(jobsListener);
		schedulerFactoryBean.setOverwriteExistingJobs(true);
		schedulerFactoryBean.setDataSource(dataSource);
		schedulerFactoryBean.setQuartzProperties(properties);
		schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true);
		return schedulerFactoryBean;
	}

	/**
	 * Spring Framework의 Shutdown Hook 설정.
	 * Quartz의 Shutdown 동작을 위임받아 Graceful Shutdown 을 보장.
	 * <p>
	 * https://github.com/kingbbode/spring-batch-quartz/blob/master/src/main/java/com/kingbbode/config/BatchConfiguration.java
	 *
	 * @param schedulerFactoryBean the schedulerFactoryBean
	 * @return SmartLifecycle
	 */
	@Bean
	public SmartLifecycle gracefulShutdownHookForQuartz(@Qualifier("schedulerFactoryBean") SchedulerFactoryBean schedulerFactoryBean) {
		return new SmartLifecycle() {
			private boolean isRunning = false;

			@Override
			public boolean isAutoStartup() {
				return true;
			}

			@Override
			public void stop(Runnable callback) {
				stop();
				log.info("Spring container is shutting down.");
				callback.run();
			}

			@Override
			public void start() {
				log.info("Quartz Graceful Shutdown Hook started.");
				isRunning = true;
			}

			@Override
			public void stop() {
				isRunning = false;

				try {
					log.info("Quartz Graceful Shutdown...");
					interruptJobs(schedulerFactoryBean);
					schedulerFactoryBean.destroy();
				} catch (SchedulerException e) {
					try {
						log.info("Error shutting down Quartz: ", e);
						schedulerFactoryBean.getScheduler().shutdown(false);
					} catch (SchedulerException ex) {
						log.error("Unable to shutdown the Quartz scheduler.", ex);
					}
				}
			}

			@Override
			public boolean isRunning() {
				return isRunning;
			}

			@Override
			public int getPhase() {
				return Integer.MAX_VALUE;
			}
		};
	}

	private void interruptJobs(SchedulerFactoryBean schedulerFactoryBean) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		for (JobExecutionContext jobExecutionContext : scheduler.getCurrentlyExecutingJobs()) {
			final JobDetail jobDetail = jobExecutionContext.getJobDetail();
			log.info("interrupting job :: jobKey : {}", jobDetail.getKey());
			scheduler.interrupt(jobDetail.getKey());
		}
	}
}
