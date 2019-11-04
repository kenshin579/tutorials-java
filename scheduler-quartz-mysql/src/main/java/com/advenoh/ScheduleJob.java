package com.advenoh;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Properties;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * todo : connection refused
 */
public class ScheduleJob {
	public static void main(String[] args) {
		try {
			// Create Job
			JobDetail job = newJob(TestJOB.class).withIdentity
					("jobName", "groupName")
					.build();
			// Create Trigger
			Trigger trigger = newTrigger().withIdentity
					("triggerName", "groupName")
					.startNow().withSchedule(simpleSchedule()
							.withIntervalInSeconds(10)
							.repeatForever())
					.build();
			//Quartz Server Properties
			Properties prop = new Properties();
			prop.put("org.quartz.scheduler.rmi.proxy", "true");
			prop.put("org.quartz.scheduler.rmi.registryHost", "localhost");
			prop.put("org.quartz.scheduler.rmi.registryPort", "1098");
			prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
			prop.put("org.quartz.threadPool.threadCount", "1");
			Scheduler scheduler = new StdSchedulerFactory(prop).getScheduler();
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
