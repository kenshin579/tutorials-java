package com.advenoh.service;

import com.advenoh.model.JobHistory;
import com.advenoh.model.JobStatus;
import com.advenoh.model.JobType;
import com.advenoh.model.StateType;
import com.advenoh.repository.JobHistoryRepository;
import com.advenoh.repository.JobStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JobHistoryServiceTest {
    @Autowired
    @Qualifier("jobHistoryService")
    private JobHistoryService jobHistoryService;

    @Autowired
    private JobHistoryRepository jobHistoryRepository;

    @Autowired
    private JobStatusRepository jobStatusRepository;

    private String testJobName;
    private String testGroupName;
    private JobType testJobType;

    @Before
    public void setUp() throws Exception {
        testJobName = UUID.randomUUID().toString();
        testGroupName = "group1";
        testJobType = JobType.CRON;
    }

    @Test
    @Transactional
    public void deleteJob() {
        createJobHistory(testJobName, testGroupName, testJobType);

        JobKey jobKey = new JobKey(testJobName, testGroupName);
        JobStatus jobStatus = jobHistoryService.deleteJob(jobKey);
        assertThat(jobStatus.getJobHistory().getJobName()).isEqualTo(testJobName);
        assertThat(jobStatus.getJobHistory().getJobGroup()).isEqualTo(testGroupName);
        assertThat(jobStatus.getJobState()).isEqualTo(StateType.DELETE);
    }

    @Test
    @Transactional
    public void pauseJob() {
        createJobHistory(testJobName, testGroupName, testJobType);

        JobKey jobKey = new JobKey(testJobName, testGroupName);
        JobStatus jobStatus = jobHistoryService.pauseJob(jobKey);
        assertThat(jobStatus.getJobHistory().getJobName()).isEqualTo(testJobName);
        assertThat(jobStatus.getJobHistory().getJobGroup()).isEqualTo(testGroupName);
        assertThat(jobStatus.getJobState()).isEqualTo(StateType.PAUSE);
    }

    @Test
    @Transactional
    public void resumeJob() {
        createJobHistory(testJobName, testGroupName, testJobType);

        JobKey jobKey = new JobKey(testJobName, testGroupName);
        JobStatus jobStatus = jobHistoryService.resumeJob(jobKey);
        assertThat(jobStatus.getJobHistory().getJobName()).isEqualTo(testJobName);
        assertThat(jobStatus.getJobHistory().getJobGroup()).isEqualTo(testGroupName);
        assertThat(jobStatus.getJobState()).isEqualTo(StateType.RESUME);
    }

    @Test
    @Transactional
    public void stopJob() {
        createJobHistory(testJobName, testGroupName, testJobType);

        JobKey jobKey = new JobKey(testJobName, testGroupName);
        JobStatus jobStatus = jobHistoryService.stopJob(jobKey);
        assertThat(jobStatus.getJobHistory().getJobName()).isEqualTo(testJobName);
        assertThat(jobStatus.getJobHistory().getJobGroup()).isEqualTo(testGroupName);
        assertThat(jobStatus.getJobState()).isEqualTo(StateType.STOP);
    }

    @Test
    @Transactional
    public void getAllJobs() {
        createJobHistory(testJobName, testGroupName, testJobType);
        Pageable pageRequest = PageRequest.of(0, 5, Sort.by("statusId").descending());
        Page<JobStatus> statusPage = jobHistoryService.getAllJobs(pageRequest);
        assertThat(statusPage.getContent().get(0).getJobHistory().getJobName()).isEqualTo(testJobName);
        assertThat(statusPage.getContent().get(0).getJobHistory().getJobGroup()).isEqualTo(testGroupName);
        assertThat(statusPage.getContent().get(0).getJobState()).isEqualTo(StateType.CREATE);
    }

    private void createJobHistory(String testJobName, String testGroupName, JobType jobType) {
        JobHistory jobHistory = new JobHistory();
        jobHistory.setJobName(testJobName);
        jobHistory.setJobGroup(testGroupName);
        jobHistory.setJobType(jobType);
        jobHistoryRepository.save(jobHistory);

        JobStatus jobStatus = new JobStatus();
        jobStatus.setCreateDt(new Date());
        jobStatus.setJobState(StateType.CREATE);
        jobStatus.setJobHistory(jobHistory);
        jobStatusRepository.save(jobStatus);
    }
}