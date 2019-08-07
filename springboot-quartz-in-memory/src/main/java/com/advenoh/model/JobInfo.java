package com.advenoh.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.quartz.JobDataMap;

import java.util.Date;

@Getter
@Setter
@Builder
@ToString
public class JobInfo {
	private String jobGroup;
	private String jobName;

	private Date startDateAt;
	private long repeatIntervalInSeconds;
	private int repeatCount;

	private String cronExpression;
	private JobDataMap jobDataMap;
}
