package com.advenoh.service;

import com.advenoh.dto.JobHistoryResponse;
import com.advenoh.dto.JobRequest;
import com.advenoh.exception.ResourceNotFoundException;
import com.advenoh.model.JobHistory;
import com.advenoh.model.JobStatus;
import com.advenoh.model.JobType;
import com.advenoh.model.StateType;
import com.advenoh.repository.JobHistoryRepository;
import com.advenoh.repository.JobStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service("jobHistoryService")
public class JobHistoryService {

    @Autowired
    private JobHistoryRepository jobHistoryRepository;

    @Autowired
    private JobStatusRepository jobStatusRepository;

    public JobHistory addJob(JobRequest jobRequest, JobType jobType) {
        JobHistory jobHistory = new JobHistory();
        jobHistory.setJobName(jobRequest.getJobName());
        jobHistory.setJobGroup(jobRequest.getJobGroup());
        jobHistory.setJobType(jobType);
        jobHistory = jobHistoryRepository.save(jobHistory);

        JobStatus jobStatus = new JobStatus();
        jobStatus.setJobState(StateType.CREATE);
        jobStatus.setJobHistory(jobHistory);
        jobStatusRepository.save(jobStatus);
        return jobHistory;
    }

    public JobStatus deleteJob(JobKey jobKey) {
        return saveJobStatus(jobKey, StateType.DELETE);
    }

    public JobStatus updateJob(JobKey jobKey) {
        return saveJobStatus(jobKey, StateType.UPDATE);
    }

    public JobStatus pauseJob(JobKey jobKey) {
        return saveJobStatus(jobKey, StateType.PAUSE);
    }

    public JobStatus resumeJob(JobKey jobKey) {
        return saveJobStatus(jobKey, StateType.RESUME);
    }

    public JobStatus stopJob(JobKey jobKey) {
        return saveJobStatus(jobKey, StateType.STOP);
    }

    public Page<JobHistory> getAllJobs(Pageable pageable) {
        log.info("[FRANK] pageable : {}", pageable);
        JobHistoryResponse jobHistoryResponse = new JobHistoryResponse();

        Page<JobHistory> jobHistories = jobHistoryRepository.findAll(pageable);
        log.info("[FRANK] jobHistories : {}", jobHistories);

//        for (JobHistory jobHistory : jobHistories) {
//            log.info("[FRANK] jobHistory : {}", jobHistory);
//
//        }

        jobHistories.map(jobHistory -> {
//            jobStatusRepository.findBy;
            return jobHistory;
        });

        return jobHistories;
    }

    private JobStatus saveJobStatus(JobKey jobKey, StateType delete) {
        JobHistory jobHistory = jobHistoryRepository
                .findFirstByJobNameAndJobGroupOrderByHistoryIdDesc(jobKey.getName(), jobKey.getGroup())
                .orElseThrow(() -> new ResourceNotFoundException("jobKey " + jobKey + " not found"));
        jobHistory.setUpdateDt(new Date());

        jobHistoryRepository.save(jobHistory);

        JobStatus jobStatus = new JobStatus();
        jobStatus.setJobState(delete);
        jobStatus.setJobHistory(jobHistory);
        return jobStatusRepository.save(jobStatus);
    }
}
