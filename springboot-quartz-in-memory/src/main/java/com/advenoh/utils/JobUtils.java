package com.advenoh.utils;

import com.advenoh.model.JobInfo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.Date;

import static org.quartz.CronExpression.isValidExpression;

@Slf4j
public final class JobUtils {

	private JobUtils() {
	}

	public static JobDetail createJob(JobInfo jobInfo, Class<? extends QuartzJobBean> jobClass, ApplicationContext context) {
		JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
		factoryBean.setJobClass(jobClass);
		factoryBean.setDurability(false);
		factoryBean.setApplicationContext(context);
		factoryBean.setName(jobInfo.getJobName());
		factoryBean.setGroup(jobInfo.getJobGroup());

		if (jobInfo.getJobDataMap() != null) {
			factoryBean.setJobDataMap(jobInfo.getJobDataMap());
		}

		factoryBean.afterPropertiesSet();
		return factoryBean.getObject();
	}

	public static Trigger createTrigger(JobInfo jobInfo) {
		String cronExpression = jobInfo.getCronExpression();
		Date startDateAt = jobInfo.getStartDateAt();

		if (!StringUtils.isEmpty(cronExpression)) {
			if (!isValidExpression(cronExpression)) {
				throw new IllegalArgumentException("Provided expression " + cronExpression + " is not a valid cron expression");
			}
			return createCronTrigger(jobInfo);
		} else if (startDateAt != null) {
			return createSimpleTrigger(jobInfo);
		}
		throw new IllegalStateException("unsupported trigger descriptor");
	}

	private static Trigger createCronTrigger(JobInfo jobInfo) {
		CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
		factoryBean.setName(jobInfo.getJobName());
		factoryBean.setGroup(jobInfo.getJobGroup());
		factoryBean.setCronExpression(jobInfo.getCronExpression());
		factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
		try {
			factoryBean.afterPropertiesSet();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return factoryBean.getObject();
	}

	private static Trigger createSimpleTrigger(JobInfo jobInfo) {
		SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
		factoryBean.setName(jobInfo.getJobName());
		factoryBean.setGroup(jobInfo.getJobGroup());
		factoryBean.setStartTime(jobInfo.getStartDateAt());
		factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
		factoryBean.setRepeatInterval(jobInfo.getRepeatIntervalInSeconds() * 1000); //ms 단위임
		factoryBean.setRepeatCount(jobInfo.getRepeatCount());

		factoryBean.afterPropertiesSet();
		return factoryBean.getObject();
	}
}
