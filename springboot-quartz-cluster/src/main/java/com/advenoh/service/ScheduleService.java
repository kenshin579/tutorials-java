package com.advenoh.service;

import com.advenoh.dto.JobRequest;
import com.advenoh.dto.JobStatusResponse;
import org.quartz.JobKey;
import org.springframework.scheduling.quartz.QuartzJobBean;

public interface ScheduleService {
    JobStatusResponse getAllJobs();

    boolean isJobRunning(JobKey jobKey);

    boolean isJobExists(JobKey jobKey);

    boolean addJob(JobRequest jobRequest, Class<? extends QuartzJobBean> jobClass);

    boolean deleteJob(JobKey jobKey);

    boolean pauseJob(JobKey jobKey);

    boolean resumeJob(JobKey jobKey);

    String getJobState(JobKey jobKey);
}
