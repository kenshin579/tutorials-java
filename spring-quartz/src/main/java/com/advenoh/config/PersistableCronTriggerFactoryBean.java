package com.advenoh.config;

import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

import java.text.ParseException;

/**
 * Needed to set Quartz useProperties=true when using Spring classes,
 * because Spring sets an object reference on JobDataMap that is not a String
 *
 * @see http://site.trimplement.com/using-spring-and-quartz-with-jobstore-properties/
 * @see http://forum.springsource.org/showthread.php?130984-Quartz-error-IOException
 * @see https://stackoverflow.com/questions/17150957/configuring-crontriggerfactorybean-for-quartz-clustering-with-jobstoretx
 */
public class PersistableCronTriggerFactoryBean extends CronTriggerFactoryBean {

	public static final String JOB_DETAIL_KEY = "jobDetail";

	@Override
	public void afterPropertiesSet() throws ParseException {
		super.afterPropertiesSet();

		//Remove the JobDetail element
		getJobDataMap().remove(JOB_DETAIL_KEY);
	}
}