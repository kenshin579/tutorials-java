package com.advenoh.controller;

import com.advenoh.dto.JobRequest;
import com.advenoh.job.CronJob;
import com.advenoh.job.SimpleJob;
import com.advenoh.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/scheduler")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping(value = "/job", method = RequestMethod.POST)
    public Map<String, ?> addScheduleJob(@ModelAttribute JobRequest jobRequest) {
        log.debug("add schedule job :: jobRequest : {}", jobRequest);
        Map<String, Object> result = new HashMap<>();
        boolean status = false;

        if (jobRequest.getJobName() == null) {
            throw new RuntimeException("require jobName");
        }

        JobKey jobKey = new JobKey(jobRequest.getJobName(), jobRequest.getJobGroup());
        if (!scheduleService.isJobExists(jobKey)) {
            if (jobRequest.getCronExpression() == null) {
                status = scheduleService.addJob(jobRequest, SimpleJob.class);
            } else {
                status = scheduleService.addJob(jobRequest, CronJob.class);
            }
        } else {
            throw new RuntimeException("job already exits");
        }
        result.put("status", status);
        return result;
    }

    @RequestMapping(value = "/job", method = RequestMethod.DELETE)
    public Map<String, ?> deleteScheduleJob(@ModelAttribute JobRequest jobRequest) {
        JobKey jobKey = new JobKey(jobRequest.getJobName(), jobRequest.getJobGroup());
        Map<String, Object> result = new HashMap<>();
        boolean status;

        if (scheduleService.isJobExists(jobKey)) {
            if (!scheduleService.isJobRunning(jobKey)) {
                status = scheduleService.deleteJob(jobKey);
            } else {
                throw new RuntimeException("job already in running state");
            }
        } else {
            throw new RuntimeException("job does not exist");
        }
        result.put("status", status);
        return result;
    }

    @RequestMapping(value = "/jobs", method = RequestMethod.GET)
    public Map<String, ?> getAllJobs() {
        return scheduleService.getAllJobs();
    }

    @RequestMapping(value = "/job/pause", method = RequestMethod.PUT)
    public Map<String, ?> pauseJob(@ModelAttribute JobRequest jobRequest) {
        JobKey jobKey = new JobKey(jobRequest.getJobName(), jobRequest.getJobGroup());
        Map<String, Object> result = new HashMap<>();
        boolean status;
        if (scheduleService.isJobExists(jobKey)) {
            if (!scheduleService.isJobRunning(jobKey)) {
                status = scheduleService.pauseJob(jobKey);
            } else {
                throw new RuntimeException("job already running state");
            }
        } else {
            throw new RuntimeException("job does not exist");
        }
        result.put("status", status);
        return result;
    }

    @RequestMapping(value = "/job/resume", method = RequestMethod.PUT)
    public Map<String, ?> resumeJob(@ModelAttribute JobRequest jobRequest) {
        JobKey jobKey = new JobKey(jobRequest.getJobName(), jobRequest.getJobGroup());
        Map<String, Object> result = new HashMap<>();
        boolean status;
        if (scheduleService.isJobExists(jobKey)) {
            if (!scheduleService.isJobRunning(jobKey)) {
                status = scheduleService.resumeJob(jobKey);
            } else {
                throw new RuntimeException("job already running state");
            }
        } else {
            throw new RuntimeException("job does not exist");
        }
        result.put("status", status);
        return result;
    }
}
