package com.advenoh.service;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SchedulerJobListener implements JobListener {

	@Override
	public String getName() {
		return "globalJob";
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		String jobName = context.getJobDetail().getKey().toString();
		log.info("jobToBeExecuted : {}", context.getJobDetail());
		log.info("Job : {} is going to start...", jobName);
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		log.info("JobsListener.jobExecutionVetoed() : {}", context.getJobDetail());

	}

	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		log.info("JobsListener.jobWasExecuted()");

		String jobName = context.getJobDetail().getKey().toString();
		log.info("Job : {} is finished...", jobName);

//		if (!jobException.getMessage().equals("")) {
//			log.error("Exception thrown by: {} Exception: {}", jobName, jobException.getMessage());
//		}
	}

}
