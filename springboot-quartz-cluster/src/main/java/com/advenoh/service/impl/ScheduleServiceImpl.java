package com.advenoh.service.impl;

import com.advenoh.dto.JobRequest;
import com.advenoh.dto.JobResponse;
import com.advenoh.dto.JobStatusResponse;
import com.advenoh.model.JobHistory;
import com.advenoh.model.JobType;
import com.advenoh.service.JobHistoryService;
import com.advenoh.service.ScheduleService;
import com.advenoh.utils.DateTimeUtils;
import com.advenoh.utils.JobUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private JobHistoryService jobHistoryService;

    @Autowired
    private ApplicationContext context;

    @Override
    public boolean addJob(JobRequest jobRequest, Class<? extends Job> jobClass) {
        JobKey jobKey = null;
        JobDetail jobDetail;
        Trigger trigger;

        try {
            trigger = JobUtils.createTrigger(jobRequest);
            jobDetail = JobUtils.createJob(jobRequest, jobClass, context);
            jobKey = JobKey.jobKey(jobRequest.getJobName(), jobRequest.getJobGroup());

            JobHistory jobHistory = jobHistoryService.addJob(jobRequest, jobRequest.getCurrentJobType());
            log.debug("jobHistory : {}", jobHistory);

            Date dt = schedulerFactoryBean.getScheduler().scheduleJob(jobDetail, trigger);
            log.debug("Job with jobKey : {} scheduled successfully at date : {}", jobDetail.getKey(), dt);
            return true;
        } catch (SchedulerException e) {
            log.error("error occurred while scheduling with jobKey : {}", jobKey, e);
        }
        return false;
    }

    @Override
    public boolean deleteJob(JobKey jobKey) {
        log.debug("[schedulerdebug] deleting job with jobKey : {}", jobKey);
        try {
            jobHistoryService.deleteJob(jobKey);
            return schedulerFactoryBean.getScheduler().deleteJob(jobKey);
        } catch (SchedulerException e) {
            log.error("[schedulerdebug] error occurred while deleting job with jobKey : {}", jobKey, e);
        }
        return false;
    }

    @Override
    public boolean pauseJob(JobKey jobKey) {
        log.debug("[schedulerdebug] pausing job with jobKey : {}", jobKey);
        try {
            jobHistoryService.pauseJob(jobKey);
            schedulerFactoryBean.getScheduler().pauseJob(jobKey);
            return true;
        } catch (SchedulerException e) {
            log.error("[schedulerdebug] error occurred while deleting job with jobKey : {}", jobKey, e);
        }
        return false;
    }

    @Override
    public boolean resumeJob(JobKey jobKey) {
        log.debug("[schedulerdebug] resuming job with jobKey : {}", jobKey);
        try {
            jobHistoryService.resumeJob(jobKey);
            schedulerFactoryBean.getScheduler().resumeJob(jobKey);
            return true;
        } catch (SchedulerException e) {
            log.error("[schedulerdebug] error occurred while resuming job with jobKey : {}", jobKey, e);
        }
        return false;
    }

    @Override
    public boolean stopJob(JobKey jobKey) {
        log.debug("[schedulerdebug] stopping job with jobKey : {}", jobKey);
        try {
            jobHistoryService.stopJob(jobKey);
            return schedulerFactoryBean.getScheduler().interrupt(jobKey);
        } catch (SchedulerException e) {
            log.error("[schedulerdebug] error occurred while stopping job with jobKey : {}", jobKey, e);
        }
        return false;
    }

    @Override
    public JobStatusResponse getAllJobs() {
        JobResponse jobResponse;
        JobStatusResponse jobStatusResponse = new JobStatusResponse();
        List<JobResponse> jobs = new ArrayList<>();
        int numOfRunningJobs = 0;
        int numOfGroups = 0;
        int numOfAllJobs = 0;

        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            for (String groupName : scheduler.getJobGroupNames()) {
                numOfGroups++;
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);

                    jobResponse = JobResponse.builder()
                            .jobName(jobKey.getName())
                            .groupName(jobKey.getGroup())
                            .scheduleTime(DateTimeUtils.toString(triggers.get(0).getStartTime()))
                            .lastFiredTime(DateTimeUtils.toString(triggers.get(0).getPreviousFireTime()))
                            .nextFireTime(DateTimeUtils.toString(triggers.get(0).getNextFireTime()))
                            .build();

                    if (isJobRunning(jobKey)) {
                        jobResponse.setJobStatus("RUNNING");
                        numOfRunningJobs++;
                    } else {
                        String jobState = getJobState(jobKey);
                        jobResponse.setJobStatus(jobState);
                    }
                    numOfAllJobs++;
                    jobs.add(jobResponse);
                }
            }
        } catch (SchedulerException e) {
            log.error("[schedulerdebug] error while fetching all job info", e);
        }

        jobStatusResponse.setNumOfAllJobs(numOfAllJobs);
        jobStatusResponse.setNumOfRunningJobs(numOfRunningJobs);
        jobStatusResponse.setNumOfGroups(numOfGroups);
        jobStatusResponse.setJobs(jobs);
        return jobStatusResponse;
    }

    @Override
    public boolean isJobRunning(JobKey jobKey) {
        try {
            List<JobExecutionContext> currentJobs = schedulerFactoryBean.getScheduler().getCurrentlyExecutingJobs();
            if (currentJobs != null) {
                for (JobExecutionContext jobCtx : currentJobs) {
                    if (jobKey.getName().equals(jobCtx.getJobDetail().getKey().getName())) {
                        return true;
                    }
                }
            }
        } catch (SchedulerException e) {
            log.error("[schedulerdebug] error occurred while checking job with jobKey : {}", jobKey, e);
        }
        return false;
    }

    @Override
    public boolean isJobExists(JobKey jobKey) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            if (scheduler.checkExists(jobKey)) {
                return true;
            }
        } catch (SchedulerException e) {
            log.error("[schedulerdebug] error occurred while checking job exists :: jobKey : {}", jobKey, e);
        }
        return false;
    }

    @Override
    public String getJobState(JobKey jobKey) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);

            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobDetail.getKey());

            if (triggers != null && triggers.size() > 0) {
                for (Trigger trigger : triggers) {
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    if (Trigger.TriggerState.NORMAL.equals(triggerState)) {
                        return "SCHEDULED";
                    }
                    return triggerState.name().toUpperCase();
                }
            }
        } catch (SchedulerException e) {
            log.error("[schedulerdebug] Error occurred while getting job state with jobKey : {}", jobKey, e);
        }
        return null;
    }
}
