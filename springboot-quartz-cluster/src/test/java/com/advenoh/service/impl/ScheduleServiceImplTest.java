package com.advenoh.service.impl;

import com.advenoh.dto.JobRequest;
import com.advenoh.dto.JobStatusResponse;
import com.advenoh.job.CronJob;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.JobExecutionContextImpl;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class ScheduleServiceImplTest {
    @InjectMocks
    private ScheduleServiceImpl scheduleService;
    @Mock
    private SchedulerFactoryBean schedulerFactoryBean;

    @Mock
    private Scheduler scheduler;

    final private String jobName = "job1";
    final private String groupName = "group1";
    private List<String> jobGroupNames;
    private Set<JobKey> jobKeySet;

    @Before
    public void setUp() {
        jobGroupNames = Arrays.asList(groupName);
        jobKeySet = Sets.newHashSet(new JobKey(jobName, groupName));
    }

    @Test
    public void addJob() {
        JobRequest jobRequest = new JobRequest();
        jobRequest.setCronExpression("0/10 * * ? * *");
        jobRequest.setJobName(jobName);
        jobRequest.setJobGroup(groupName);

        when(schedulerFactoryBean.getScheduler()).thenReturn(scheduler);

        boolean result = scheduleService.addJob(jobRequest, CronJob.class);
        assertThat(result).isTrue();

        verify(schedulerFactoryBean).getScheduler();
    }

    @Test
    public void deleteJob() throws SchedulerException {
        JobKey jobKey = new JobKey(jobName, groupName);

        when(schedulerFactoryBean.getScheduler()).thenReturn(scheduler);
        when(scheduler.deleteJob(jobKey)).thenReturn(true);

        boolean result = scheduleService.deleteJob(jobKey);
        assertThat(result).isTrue();

        verify(schedulerFactoryBean).getScheduler();
        verify(scheduler).deleteJob(jobKey);
    }

    @Test
    public void pauseJob() throws SchedulerException {
        JobKey jobKey = new JobKey(jobName, groupName);

        when(schedulerFactoryBean.getScheduler()).thenReturn(scheduler);
        doNothing().when(scheduler).pauseJob(jobKey);

        boolean result = scheduleService.pauseJob(jobKey);
        assertThat(result).isTrue();

        verify(schedulerFactoryBean).getScheduler();
        verify(scheduler).pauseJob(jobKey);
    }

    @Test
    public void resumeJob() throws SchedulerException {
        JobKey jobKey = new JobKey(jobName, groupName);

        when(schedulerFactoryBean.getScheduler()).thenReturn(scheduler);
        doNothing().when(scheduler).resumeJob(jobKey);

        boolean result = scheduleService.resumeJob(jobKey);
        assertThat(result).isTrue();

        verify(schedulerFactoryBean).getScheduler();
        verify(scheduler).resumeJob(jobKey);
    }

    @Test
    public void getAllJobs() throws SchedulerException {
        List triggers = new ArrayList();
        addMockTrigger(triggers, jobName, groupName);

        when(schedulerFactoryBean.getScheduler()).thenReturn(scheduler);
        when(scheduler.getJobGroupNames()).thenReturn(jobGroupNames);
        when(scheduler.getJobKeys(anyObject())).thenReturn(jobKeySet);
        when(scheduler.getJobDetail(anyObject())).thenReturn(new JobDetailImpl(jobName, groupName, MockJob.class));
        when(scheduler.getTriggersOfJob(anyObject())).thenReturn(triggers).thenReturn(triggers);
        when(scheduler.getTriggerState(anyObject())).thenReturn(Trigger.TriggerState.BLOCKED);

        JobStatusResponse result = scheduleService.getAllJobs();
        log.info("result : {}", result);
        assertThat(result.getJobs().size()).isEqualTo(1);
        assertThat(result.getJobs().get(0).getJobName()).isEqualTo(jobName);

        verify(schedulerFactoryBean, times(3)).getScheduler();
        verify(scheduler).getJobGroupNames();
        verify(scheduler).getJobKeys(anyObject());
        verify(scheduler).getJobDetail(anyObject());
        verify(scheduler, times(2)).getTriggersOfJob(anyObject());
        verify(scheduler).getTriggerState(anyObject());
    }

    @Test
    public void isJobExists() throws SchedulerException {
        JobKey jobKey = new JobKey(jobName, groupName);

        when(schedulerFactoryBean.getScheduler()).thenReturn(scheduler);
        when(scheduler.checkExists(jobKey)).thenReturn(true);

        boolean result = scheduleService.isJobExists(jobKey);
        assertThat(result).isTrue();

        verify(schedulerFactoryBean).getScheduler();
        verify(scheduler).checkExists(jobKey);
    }

    @Test
    public void isJobRunning() throws SchedulerException {
        JobKey jobKey = new JobKey(jobName, groupName);
        List<JobExecutionContext> currentJobs = new ArrayList<>();

        JobExecutionContext jobExecutionContext = new JobExecutionContextImpl(
                scheduler,
                new TriggerFiredBundle(new JobDetailImpl(jobName, groupName, MockJob.class),
                        new CronTriggerImpl(jobName), null, false, null, null, null, null),
                new MockJob());

        currentJobs.add(jobExecutionContext);

        when(schedulerFactoryBean.getScheduler()).thenReturn(scheduler);
        when(scheduler.getCurrentlyExecutingJobs()).thenReturn(currentJobs);

        boolean result = scheduleService.isJobRunning(jobKey);
        assertThat(result).isTrue();
        verify(schedulerFactoryBean).getScheduler();
        verify(scheduler).getCurrentlyExecutingJobs();
    }


    private void addMockTrigger(List list, String jobName, String groupName) {
        Trigger trigger = mock(Trigger.class);
        TriggerKey triggerKey = new TriggerKey(jobName, groupName);
        when(trigger.getKey()).thenReturn(triggerKey);
        list.add(trigger);
    }

    class MockJob extends QuartzJobBean implements Job {
        @Override
        protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
            //ok
        }
    }
}