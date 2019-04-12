package com.advenoh.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.util.Date;

@Slf4j
public class TestJob implements Job {
	private volatile Thread currThread;

	@Override
	public void execute(final JobExecutionContext context)
			throws JobExecutionException {

		currThread = Thread.currentThread();
		log.debug("============================================================================");
		JobKey key = context.getJobDetail().getKey();
		log.info("CronJob started with key :" + key.getName() + ", Group :" + key.getGroup() + " , Thread Name :" + currThread.getName()
				+ " ,Time now :" + new Date());

		log.debug("Thread: " + Thread.currentThread().getName() + " stopped.");
		log.debug("============================================================================");

	}

}
