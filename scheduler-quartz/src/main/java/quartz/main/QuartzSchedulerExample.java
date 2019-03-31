package quartz.main;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import quartz.ILatch;
import quartz.Job.MyJob;

import java.util.concurrent.CountDownLatch;

public class QuartzSchedulerExample implements ILatch {
	private int repeatCount = 3;
	private CountDownLatch latch = new CountDownLatch(repeatCount + 1);

	public static void main(String[] args) throws Exception {
		QuartzSchedulerExample quartzSchedulerExample = new QuartzSchedulerExample();
		quartzSchedulerExample.fireJob();
	}

	public void fireJob() throws SchedulerException, InterruptedException {
		SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
		Scheduler scheduler = schedFact.getScheduler();
		scheduler.start();

		// define the job and tie it to our HelloJob class
		JobBuilder jobBuilder = JobBuilder.newJob(MyJob.class);
		JobDataMap data = new JobDataMap();
		data.put("latch", this);

		JobDetail jobDetail = jobBuilder
				.usingJobData("example", "com.javacodegeeks.quartz.main.QuartzSchedulerExample")
				.usingJobData(data)
				.withIdentity("myJob", "group1")
				.build();

		// Trigger the job to run now, and then every 40 seconds
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("myTrigger", "group1")
				.startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
						.withRepeatCount(repeatCount)
						.withIntervalInSeconds(2))
				.build();

		// Tell quartz to schedule the job using our trigger
		scheduler.scheduleJob(jobDetail, trigger);
		latch.await();
		System.out.println("All triggers executed. Shutdown scheduler");
		scheduler.shutdown();
	}

	public void countDown() {
		latch.countDown();
	}
}
