package com.advenoh.dto.history;

import kr.pe.advenoh.model.JobType;
import kr.pe.advenoh.model.StateType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@Deprecated
public class JobHistoryStatusResponse {
    private Long statusId;
    private StateType jobState;
    private Date createDt;

    private String jobName;
    private String jobGroup;
    private JobType jobType;
}
