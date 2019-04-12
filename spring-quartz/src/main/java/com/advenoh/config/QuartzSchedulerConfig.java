package com.advenoh.config;

import com.advenoh.service.SchedulerJobListener;
import com.advenoh.service.SchedulerTriggerListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Slf4j
@Configuration
@PropertySource({ "classpath:jdbc.properties" })
@EnableTransactionManagement
public class QuartzSchedulerConfig {

	@Autowired
	DataSource dataSource;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private SchedulerTriggerListener triggerListner;

	@Autowired
	private SchedulerJobListener jobsListener;

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
		log.info("[FRANK] schedulerFactoryBean");
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setQuartzProperties(quartzProperties());
		schedulerFactoryBean.setOverwriteExistingJobs(true);
		schedulerFactoryBean.setDataSource(dataSource);
		schedulerFactoryBean.setNonTransactionalDataSource(dataSource);
		schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true);

		//Register listeners to get notification on Trigger misfire etc
		schedulerFactoryBean.setGlobalTriggerListeners(triggerListner);
		schedulerFactoryBean.setGlobalJobListeners(jobsListener);

		AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
		jobFactory.setApplicationContext(applicationContext);
		schedulerFactoryBean.setJobFactory(jobFactory);

		return schedulerFactoryBean;
	}

	@Bean
	public Properties quartzProperties() {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));

		Properties properties = null;
		try {
			propertiesFactoryBean.afterPropertiesSet();
			properties = propertiesFactoryBean.getObject();
		} catch (Exception e) {
			log.warn("Cannont load quartz.properties");
		}
		return properties;
	}

	//		/**
	//		 * Quartz 스케줄러 등록된 잡에 따라서 DB 로그가 너무 많이 찍혀서 Quartz DB 로그는 안찍히도록 수정함
	//		 *
	//		 * @param propertyPrefix
	//		 * @return
	//		 */
	//		private DataSource quartzDataSource(String propertyPrefix) {
	//			DriverManagerDataSource dataSource = new DriverManagerDataSource();
	//			dataSource.setDriverClassName(env.getProperty(propertyPrefix + ".driverClassName"));
	//			dataSource.setUrl(env.getProperty(propertyPrefix + ".url").replace("log4jdbc:", ""));
	//			dataSource.setUsername(env.getProperty(propertyPrefix + ".username"));
	//			dataSource.setPassword(env.getProperty(propertyPrefix + ".password"));
	//
	//			Properties properties = new Properties();
	//			properties.put("testWhileIdle", env.getProperty(propertyPrefix + ".testWhileIdle"));
	//			properties.put("maxIdle", env.getProperty(propertyPrefix + ".maxIdle"));
	//			properties.put("minIdle", env.getProperty(propertyPrefix + ".minIdle"));
	//			properties.put("timeBetweenEvictionRunsMillis", env.getProperty(propertyPrefix + ".timeBetweenEvictionRunsMillis"));
	//
	//			dataSource.setConnectionProperties(properties);
	//
	//			return dataSource;
	//		}
	//
	//	@PostConstruct
	//	private void initialize() throws Exception {
	//		JobDetail jobDetail;
	//		Trigger trigger;
	//		int max = 5;
	//		for (int i = 0; i < max; i++) {
	//			jobDetail = JobBuilder
	//					.newJob(TestJob.class)
	//					.withIdentity("simple" + i, "group" + i).build();
	//			trigger = TriggerBuilder
	//					.newTrigger()
	//					.withIdentity("simple" + i, "group" + i)
	//					.forJob(jobDetail)
	//					.withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ?"))
	//					.startNow().build();
	//
	//			if (!schedulerFactoryBean.getScheduler().checkExists(new TriggerKey("simple" + i, "group" + i))) {
	//				schedulerFactoryBean.getScheduler().scheduleJob(jobDetail, trigger);
	//			}
	//		}
	//	}
}
