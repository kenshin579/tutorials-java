package com.advenoh.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.UnableToInterruptJobException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

@Slf4j
public class CronJob extends QuartzJobBean implements InterruptableJob {
	private volatile boolean isJobInterrupted = true;

	private volatile Thread currThread;

	@Override
	public void executeInternal(JobExecutionContext context) throws JobExecutionException {
		currThread = Thread.currentThread();
		log.debug("============================================================================");
		JobKey key = context.getJobDetail().getKey();
		log.info("CronJob started with key :" + key.getName() + ", Group :" + key.getGroup() + " , Thread Name :" + currThread.getName()
				+ " ,Time now :" + new Date());

		try {
			log.info("");
		} finally {
			if (isJobInterrupted) {
				log.info("Job " + key.getName() + " did not complete");
			} else {
				log.info("Job " + key.getName() + " completed at " + new Date());
			}
		}

		log.debug("Thread: " + Thread.currentThread().getName() + " stopped.");
		log.debug("============================================================================");
	}

	@Override
	public void interrupt() throws UnableToInterruptJobException {
		log.info("Interrupted thread... {}", currThread.getName());
		isJobInterrupted = true;
		if (currThread != null) {
			currThread.interrupt();
		}
	}
}
