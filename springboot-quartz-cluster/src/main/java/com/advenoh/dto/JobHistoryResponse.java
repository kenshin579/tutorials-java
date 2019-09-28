package com.advenoh.dto;

import com.advenoh.model.JobStatus;
import com.advenoh.model.JobType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class JobHistoryResponse {
    private String jobName;
    private String jobGroup;
    private JobType jobType;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDt;

    private List<JobStatus> jobStatusList;
}
