package kr.pe.advenoh.job;

import kr.pe.advenoh.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.springframework.test.util.ReflectionTestUtils;

import static org.quartz.JobBuilder.newJob;

@RunWith(MockitoJUnitRunner.class)
public class SimpleJobTest {
    private final String TEST_NAME = "test_job";
    private final String TEST_GROUP = "test_group";
    private final int MAX_SLEEP_IN_SECONDS = 1;

    @InjectMocks
    private SimpleJob simpleJob;

    @Mock
    private Scheduler scheduler;

    @Test
    public void executeInternal() throws JobExecutionException {
        ReflectionTestUtils.setField(simpleJob, "MAX_SLEEP_IN_SECONDS", MAX_SLEEP_IN_SECONDS);

        simpleJob.executeInternal(TestUtils.createJobExecutionContext(scheduler, newJob(SimpleJob.class)
                .withIdentity(TEST_NAME, TEST_GROUP)
                .build(), SimpleJob.class));
    }
}