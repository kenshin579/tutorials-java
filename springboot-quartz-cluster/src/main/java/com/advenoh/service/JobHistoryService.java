package com.advenoh.service;

import com.advenoh.dto.JobRequest;
import com.advenoh.model.JobHistory;
import com.advenoh.model.JobStatus;
import com.advenoh.model.JobType;
import com.advenoh.model.StateType;
import com.advenoh.repository.JobHistoryRepository;
import com.advenoh.repository.JobStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
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
}
