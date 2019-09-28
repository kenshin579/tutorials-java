package com.advenoh.service;

import com.advenoh.model.JobHistory;
import com.advenoh.model.JobStatus;
import com.advenoh.model.JobType;
import com.advenoh.model.StateType;
import com.advenoh.repository.JobHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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
    public void getAllJobs() {
//        Pageable pageRequest = new PageRequest(, );
//        Page<JobHistory> jobHistories = jobHistoryService.getAllJobs();
//        log.info("jobHistories : {}", jobHistories);


    }

    private JobHistory createJobHistory(String testJobName, String testGroupName, JobType jobType) {
        JobHistory jobHistory = new JobHistory();
        jobHistory.setJobName(testJobName);
        jobHistory.setJobGroup(testGroupName);
        jobHistory.setJobType(jobType);
        return jobHistoryRepository.save(jobHistory);
    }
}