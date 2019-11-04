package com.advenoh.job;

import kr.pe.advenoh.utils.TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.springframework.test.util.ReflectionTestUtils;

import static org.quartz.JobBuilder.newJob;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class CronJobTest {
    private final String TEST_NAME = "test_job";
    private final String TEST_GROUP = "test_group";
    private final int MAX_SLEEP_IN_SECONDS = 1;
    @Mock
    private Scheduler scheduler;

    @InjectMocks
    private CronJob cronJob;

    @Test
    public void executeInternal() throws JobExecutionException {
        ReflectionTestUtils.setField(cronJob, "MAX_SLEEP_IN_SECONDS", MAX_SLEEP_IN_SECONDS);

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jobId", 1231231);
        cronJob.executeInternal(TestUtils.createJobExecutionContext(scheduler, newJob(CronJob.class)
                .usingJobData(jobDataMap)
                .withIdentity(TEST_NAME, TEST_GROUP)
                .build(), CronJob.class));
    }
}