package com.advenoh.service.impl;

import kr.pe.advenoh.dto.scheduler.JobRequest;
import kr.pe.advenoh.dto.scheduler.StatusResponse;
import kr.pe.advenoh.exception.ExceptionCode;
import kr.pe.advenoh.job.CronJob;
import kr.pe.advenoh.model.JobHistory;
import kr.pe.advenoh.model.JobStatus;
import kr.pe.advenoh.service.JobHistoryService;
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
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
    private JobHistoryService jobHistoryService;

    @Mock
    private Scheduler scheduler;

    final private String testJobName = "job1";
    final private String testGroupName = "group1";
    private List<String> jobGroupNames;
    private Set<JobKey> jobKeySet;

    @Before
    public void setUp() {
        jobGroupNames = Arrays.asList(testGroupName);
        jobKeySet = Sets.newHashSet(new JobKey(testJobName, testGroupName));
    }

    @Test
    public void addJob() {
        JobRequest jobRequest = new JobRequest();
        jobRequest.setCronExpression("0/10 * * ? * *");
        jobRequest.setJobName(testJobName);
        jobRequest.setGroupName(testGroupName);

        JobHistory jobHistory = new JobHistory();
        jobHistory.setJobName(testJobName);

        when(jobHistoryService.addJob(jobRequest)).thenReturn(jobHistory);
        when(schedulerFactoryBean.getScheduler()).thenReturn(scheduler);

        boolean result = scheduleService.addJob(jobRequest, CronJob.class);
        assertThat(result).isTrue();

        verify(jobHistoryService).addJob(jobRequest);
        verify(schedulerFactoryBean).getScheduler();
    }

    @Test
    public void addJob_Exception시_Throw하는지_체크() {
        JobRequest jobRequest = new JobRequest();
        jobRequest.setCronExpression("0/10 * * ? * *");
        jobRequest.setJobName(testJobName);
        jobRequest.setGroupName(testGroupName);

        when(schedulerFactoryBean.getScheduler()).thenAnswer(invocation -> {
            throw new SchedulerException();
        });

        assertThatThrownBy(() -> scheduleService.addJob(jobRequest, CronJob.class)).hasMessage(ExceptionCode.SCHEDULER_ADD_FAIL.getMessage());
    }

    @Test
    public void deleteJob() throws SchedulerException {
        JobKey jobKey = new JobKey(testJobName, testGroupName);

        JobStatus jobStatus = new JobStatus();

        when(jobHistoryService.deleteJob(jobKey)).thenReturn(jobStatus);
        when(schedulerFactoryBean.getScheduler()).thenReturn(scheduler);
        when(scheduler.deleteJob(jobKey)).thenReturn(true);

        boolean result = scheduleService.deleteJob(jobKey);
        assertThat(result).isTrue();

        verify(jobHistoryService).deleteJob(jobKey);
        verify(schedulerFactoryBean).getScheduler();
        verify(scheduler).deleteJob(jobKey);
    }

    @Test
    public void deleteJob_Exception시_Throw하는지_체크() {
        JobKey jobKey = new JobKey(testJobName, testGroupName);

        when(schedulerFactoryBean.getScheduler()).thenAnswer(invocation -> {
            throw new SchedulerException();
        });

        assertThatThrownBy(() -> scheduleService.deleteJob(jobKey)).hasMessage(ExceptionCode.SCHEDULER_DELETE_FAIL.getMessage());
    }

    @Test
    public void updateJob() throws SchedulerException {
        JobKey jobKey = new JobKey(testJobName, testGroupName);

        JobRequest jobRequest = new JobRequest();
        jobRequest.setCronExpression("0/10 * * ? * *");
        jobRequest.setJobName(testJobName);
        jobRequest.setGroupName(testGroupName);

        JobStatus jobStatus = new JobStatus();

        when(jobHistoryService.updateJob(jobKey)).thenReturn(jobStatus);
        when(schedulerFactoryBean.getScheduler()).thenReturn(scheduler);
        when(scheduler.rescheduleJob(anyObject(), anyObject())).thenReturn(new Date());

        boolean result = scheduleService.updateJob(jobRequest);
        assertThat(result).isTrue();

        verify(jobHistoryService).updateJob(jobKey);
        verify(schedulerFactoryBean).getScheduler();
        verify(scheduler).rescheduleJob(anyObject(), anyObject());
    }

    @Test
    public void pauseJob() throws SchedulerException {
        JobKey jobKey = new JobKey(testJobName, testGroupName);

        JobStatus jobStatus = new JobStatus();

        when(jobHistoryService.pauseJob(jobKey)).thenReturn(jobStatus);
        when(schedulerFactoryBean.getScheduler()).thenReturn(scheduler);
        doNothing().when(scheduler).pauseJob(jobKey);

        boolean result = scheduleService.pauseJob(jobKey);
        assertThat(result).isTrue();

        verify(jobHistoryService).pauseJob(jobKey);
        verify(schedulerFactoryBean).getScheduler();
        verify(scheduler).pauseJob(jobKey);
    }

    @Test
    public void resumeJob() throws SchedulerException {
        JobKey jobKey = new JobKey(testJobName, testGroupName);

        JobStatus jobStatus = new JobStatus();

        when(jobHistoryService.resumeJob(jobKey)).thenReturn(jobStatus);
        when(schedulerFactoryBean.getScheduler()).thenReturn(scheduler);
        doNothing().when(scheduler).resumeJob(jobKey);

        boolean result = scheduleService.resumeJob(jobKey);
        assertThat(result).isTrue();

        verify(jobHistoryService).resumeJob(jobKey);
        verify(schedulerFactoryBean).getScheduler();
        verify(scheduler).resumeJob(jobKey);
    }

    @Test
    public void stopJob() throws SchedulerException {
        JobKey jobKey = new JobKey(testJobName, testGroupName);

        JobStatus jobStatus = new JobStatus();

        when(jobHistoryService.stopJob(jobKey)).thenReturn(jobStatus);
        when(schedulerFactoryBean.getScheduler()).thenReturn(scheduler);
        when(scheduler.interrupt(jobKey)).thenReturn(true);

        boolean result = scheduleService.stopJob(jobKey);
        assertThat(result).isTrue();

        verify(jobHistoryService).stopJob(jobKey);
        verify(schedulerFactoryBean).getScheduler();
        verify(scheduler).interrupt(jobKey);
    }

    @Test
    public void getAllJobs() throws SchedulerException {
        List triggers = new ArrayList();
        addMockTrigger(triggers, testJobName, testGroupName);

        when(schedulerFactoryBean.getScheduler()).thenReturn(scheduler);
        when(scheduler.getJobGroupNames()).thenReturn(jobGroupNames);
        when(scheduler.getJobKeys(anyObject())).thenReturn(jobKeySet);
        when(scheduler.getJobDetail(anyObject())).thenReturn(new JobDetailImpl(testJobName, testGroupName, MockJob.class));
        when(scheduler.getTriggersOfJob(anyObject())).thenReturn(triggers).thenReturn(triggers);
        when(scheduler.getTriggerState(anyObject())).thenReturn(Trigger.TriggerState.BLOCKED);

        StatusResponse result = scheduleService.getAllJobs();
        log.info("result : {}", result);
        assertThat(result.getJobs().size()).isEqualTo(1);
        assertThat(result.getJobs().get(0).getJobName()).isEqualTo(testJobName);

        verify(schedulerFactoryBean, times(3)).getScheduler();
        verify(scheduler).getJobGroupNames();
        verify(scheduler).getJobKeys(anyObject());
        verify(scheduler).getJobDetail(anyObject());
        verify(scheduler, times(2)).getTriggersOfJob(anyObject());
        verify(scheduler).getTriggerState(anyObject());
    }

    @Test
    public void getAllJobs_Exception시_Throw하는지_체크() {
        when(schedulerFactoryBean.getScheduler()).thenAnswer(invocation -> {
            throw new SchedulerException();
        });

        assertThatThrownBy(() -> scheduleService.getAllJobs()).hasMessage(ExceptionCode.SCHEDULER_GET_FAIL.getMessage());
    }

    @Test
    public void isJobExists() throws SchedulerException {
        JobKey jobKey = new JobKey(testJobName, testGroupName);

        when(schedulerFactoryBean.getScheduler()).thenReturn(scheduler);
        when(scheduler.checkExists(jobKey)).thenReturn(true);

        boolean result = scheduleService.isJobExists(jobKey);
        assertThat(result).isTrue();

        verify(schedulerFactoryBean).getScheduler();
        verify(scheduler).checkExists(jobKey);
    }

    @Test
    public void isJobRunning() throws SchedulerException {
        JobKey jobKey = new JobKey(testJobName, testGroupName);
        List<JobExecutionContext> currentJobs = new ArrayList<>();

        JobExecutionContext jobExecutionContext = new JobExecutionContextImpl(
                scheduler,
                new TriggerFiredBundle(new JobDetailImpl(testJobName, testGroupName, MockJob.class),
                        new CronTriggerImpl(testJobName), null, false, null, null, null, null),
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