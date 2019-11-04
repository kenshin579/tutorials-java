package kr.pe.advenoh.utils;

import kr.pe.advenoh.dto.JobRequest;
import kr.pe.advenoh.job.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;

import java.time.LocalDateTime;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class JobUtilsTest {
    private final String GROUP_NAME = "test_group";
    private final String JOB_NAME = "test_job";
    private final String CRON_EXPRESSION_EVERY_10_SECONDS = "0/10 * * ? * *";

    @Test
    public void buildTrigger_cronTrigger인_경우() {
        JobRequest jobRequest = new JobRequest();
        jobRequest.setJobName(JOB_NAME);
        jobRequest.setJobGroup(GROUP_NAME);
        jobRequest.setCronExpression(CRON_EXPRESSION_EVERY_10_SECONDS);

        Trigger trigger = JobUtils.createTrigger(jobRequest);
        assertThat(trigger.getKey().toString()).isEqualTo(GROUP_NAME + "." + JOB_NAME);
        assertThat(trigger instanceof CronTrigger).isTrue();
    }

    @Test
    public void buildTrigger_simpleTrigger인_경우() {
        JobRequest jobRequest = new JobRequest();
        jobRequest.setJobName(JOB_NAME);
        jobRequest.setJobGroup(GROUP_NAME);
        jobRequest.setStartDateAt(LocalDateTime.now());

        Trigger trigger = JobUtils.createTrigger(jobRequest);
        assertThat(trigger instanceof SimpleTrigger).isTrue();
    }

    @Test
    public void buildJobDetail_withJobDataMap() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jobId", "123");

        JobRequest jobRequest = new JobRequest();
        jobRequest.setJobName(JOB_NAME);
        jobRequest.setJobGroup(GROUP_NAME);
        jobRequest.setStartDateAt(LocalDateTime.now());
        jobRequest.setJobDataMap(jobDataMap);

        JobDetail jobDetail = JobUtils.createJob(jobRequest, SimpleJob.class, null);
        assertThat(jobDetail.getKey().getName()).isEqualTo(JOB_NAME);
        assertThat(jobDetail.getJobDataMap().get("jobId")).isEqualTo("123");
    }

    @Test
    public void cronExpressionTest() {
        assertThat(CronExpression.isValidExpression(CRON_EXPRESSION_EVERY_10_SECONDS)).isTrue();
    }
}