package com.advenoh.utils;

import com.tmoncorp.media.scheduler.domain.MediaScheduleJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import java.text.ParseException;
import java.util.Date;

import static org.apache.http.util.TextUtils.isEmpty;
import static org.quartz.CronExpression.isValidExpression;

@Slf4j
public final class JobUtils {

	private JobUtils() {
	}

	public static JobDetail createJob(MediaScheduleJob mediaScheduleJob, Class<? extends QuartzJobBean> jobClass, ApplicationContext context) {
		JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
		factoryBean.setJobClass(jobClass);
		factoryBean.setDurability(false); //non-durable한 job이란 trigger가 없는 job들을 scheduler가 자동으로 삭제한다는 의미임
		factoryBean.setApplicationContext(context);
		factoryBean.setName(mediaScheduleJob.getJobName());
		factoryBean.setGroup(mediaScheduleJob.getJobGroup());

		if (mediaScheduleJob.getJobDataMap() != null) {
			factoryBean.setJobDataMap(new JobDataMap(mediaScheduleJob.getJobDataMap()));
		}

		factoryBean.afterPropertiesSet();
		return factoryBean.getObject();
	}

	public static Trigger createTrigger(MediaScheduleJob mediaScheduleJob) {
		String cronExpression = mediaScheduleJob.getCronExpression();
		Date startDateAt = mediaScheduleJob.getStartDateAt();

		if (!isEmpty(cronExpression)) {
			if (!isValidExpression(cronExpression)) {
				throw new IllegalArgumentException("Provided expression " + cronExpression + " is not a valid cron expression");
			}
			return createCronTrigger(mediaScheduleJob);
		} else if (startDateAt != null) {
			return createSimpleTrigger(mediaScheduleJob);
		}
		throw new IllegalStateException("unsupported trigger descriptor");
	}

	private static Trigger createCronTrigger(MediaScheduleJob mediaScheduleJob) {
		CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
		factoryBean.setName(mediaScheduleJob.getJobName());
		factoryBean.setGroup(mediaScheduleJob.getJobGroup());
		factoryBean.setCronExpression(mediaScheduleJob.getCronExpression());
		factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
		try {
			factoryBean.afterPropertiesSet();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return factoryBean.getObject();
	}

	private static Trigger createSimpleTrigger(MediaScheduleJob mediaScheduleJob) {
		SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
		factoryBean.setName(mediaScheduleJob.getJobName());
		factoryBean.setGroup(mediaScheduleJob.getJobGroup());
		factoryBean.setStartTime(mediaScheduleJob.getStartDateAt());
		factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
		factoryBean.setRepeatInterval(mediaScheduleJob.getRepeatIntervalInSeconds() * 1000); //ms 단위임
		factoryBean.setRepeatCount(mediaScheduleJob.getRepeatCount());

		factoryBean.afterPropertiesSet();
		return factoryBean.getObject();
	}
}
