package com.advenoh.service;

import kr.pe.advenoh.dto.JobRequest;
import kr.pe.advenoh.dto.JobStatusResponse;
import org.quartz.Job;
import org.quartz.JobKey;
import org.springframework.scheduling.quartz.QuartzJobBean;

public interface ScheduleService {
    JobStatusResponse getAllJobs();

    boolean isJobRunning(JobKey jobKey);

    boolean isJobExists(JobKey jobKey);

//    boolean addJob(JobRequest jobRequest, Class<? extends QuartzJobBean> jobClass);

    boolean addJob(JobRequest jobRequest, Class<? extends Job> jobClass);

    boolean deleteJob(JobKey jobKey);

    boolean pauseJob(JobKey jobKey);

    boolean resumeJob(JobKey jobKey);

    String getJobState(JobKey jobKey);
}
