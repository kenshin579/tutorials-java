package com.advenoh.service;

import com.advenoh.dto.JobRequest;
import org.quartz.JobKey;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Map;

public interface ScheduleService {
    Map<String, Object> getAllJobs();

    boolean isJobRunning(JobKey jobKey);

    boolean isJobExists(JobKey jobKey);

    boolean addJob(JobRequest jobRequest, Class<? extends QuartzJobBean> jobClass);

    boolean deleteJob(JobKey jobKey);

    boolean pauseJob(JobKey jobKey);

    boolean resumeJob(JobKey jobKey);
}
