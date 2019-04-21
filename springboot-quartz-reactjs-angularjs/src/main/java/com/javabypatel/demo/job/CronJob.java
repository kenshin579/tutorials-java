package com.javabypatel.demo.job;

import com.javabypatel.demo.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.InterruptableJob;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.UnableToInterruptJobException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CronJob extends QuartzJobBean implements InterruptableJob {

	private volatile boolean isJobInterrupted = true;
	private volatile Thread currThread;

	@Autowired
	JobService jobService;

	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		currThread = Thread.currentThread();

		log.info("");
		log.info("======================================");
		JobKey key = jobExecutionContext.getJobDetail().getKey();
		log.info("Cron Job started with key :" + key.getName() + ", Group :" + key.getGroup() + " , Thread Name :" + currThread.getName()
				+ " ,Time now :" + new Date());

		log.info("Accessing annotation example: " + jobService.getAllJobs());
		List<Map<String, Object>> list = jobService.getAllJobs();
		log.info("Job list :" + list);

		//*********** For retrieving stored key-value pairs ***********/
		JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
		String myValue = dataMap.getString("myKey");
		log.info("Value:" + myValue);

		try {
			for (int i = 0; i < 10; i++) {
				System.out.print(i);
				TimeUnit.SECONDS.sleep(1);
			}
			System.out.println();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (isJobInterrupted) {
				log.info("Job " + key.getName() + " did not complete");
			} else {
				log.info("Job " + key.getName() + " completed at " + new Date());
			}
		}

		log.info("Thread: " + Thread.currentThread().getName() + " stopped.");
		log.info("======================================");
		log.info("");
	}

	@Override
	public void interrupt() throws UnableToInterruptJobException {
		log.info("Interrupted thread... ");
		isJobInterrupted = true;
		if (currThread != null) {
			currThread.interrupt();
		}
	}

}