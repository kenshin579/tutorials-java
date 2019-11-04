package com.advenoh.dto.scheduler;

import kr.pe.advenoh.model.JobType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.quartz.JobDataMap;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class JobRequest {

    private String jobGroup = "DEFAULT";
    private String jobName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDateAt;
    private long repeatIntervalInSeconds;
    private int repeatCount;

    private String cronExpression;
    private JobDataMap jobDataMap;

    public boolean isJobTypeSimple() {
        return this.cronExpression == null;
    }

    public JobType getCurrentJobType() {
        return isJobTypeSimple() ? JobType.SIMPLE : JobType.CRON;
    }
}
