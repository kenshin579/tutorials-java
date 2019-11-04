package com.advenoh.repository;

import kr.pe.advenoh.model.JobHistory;
import kr.pe.advenoh.model.JobStatus;
import kr.pe.advenoh.model.JobType;
import kr.pe.advenoh.model.StateType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JobHistoryLogRepositoryTest {
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
    public void jobHistory_jobStatus_객체_save_findById로_기본_테스트() {
        JobHistory jobHistory = createJobHistory(testJobName, testGroupName, testJobType);

        JobStatus jobStatus = new JobStatus();
        jobStatus.setJobState(StateType.DELETE);
        jobStatus.setJobHistory(jobHistory);
        jobStatus = jobStatusRepository.save(jobStatus);

        JobHistory saveJobHistory = jobHistoryRepository.findById(jobHistory.getHistoryId()).orElseThrow(IllegalStateException::new);
        assertThat(saveJobHistory.getHistoryId()).isEqualTo(jobHistory.getHistoryId());
        assertThat(saveJobHistory.getJobName()).isEqualTo(testJobName);
        assertThat(saveJobHistory.getJobType()).isEqualTo(testJobType);

        JobStatus saveJobStatus = jobStatusRepository.findById(jobStatus.getStatusId()).orElseThrow(IllegalAccessError::new);
        assertThat(saveJobStatus.getStatusId()).isEqualTo(jobStatus.getStatusId());
        assertThat(saveJobStatus.getJobState()).isEqualTo(jobStatus.getJobState());
    }

    @Test
    public void findJobHistoryByJobNameAndJobGroup() {
        createJobHistory(testJobName, testGroupName, testJobType);
        List<JobHistory> result = jobHistoryRepository.findJobHistoryByJobNameAndJobGroup(testJobName, testGroupName).get();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getJobName()).isEqualTo(testJobName);
    }

    @Test
    public void findFirstByJobNameAndJobGroupAndOrderByHistoryIdDesc() {
        createJobHistory(testJobName, testGroupName, testJobType);
        JobHistory result = jobHistoryRepository.findFirstByJobNameAndJobGroupOrderByHistoryIdDesc(testJobName, testGroupName).get();
        assertThat(result.getJobName()).isEqualTo(testJobName);
        assertThat(result.getJobGroup()).isEqualTo(testGroupName);
    }

    @Test
    public void name() {

    }

    private JobHistory createJobHistory(String testJobName, String testGroupName, JobType jobType) {
        JobHistory jobHistory = new JobHistory();
        jobHistory.setJobName(testJobName);
        jobHistory.setJobGroup(testGroupName);
        jobHistory.setJobType(jobType);

        return jobHistoryRepository.save(jobHistory);
    }
}