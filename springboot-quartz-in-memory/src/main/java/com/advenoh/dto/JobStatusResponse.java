package com.advenoh.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JobStatusResponse {
	private int numOfAllJobs;
	private int numOfGroups;
	private int numOfRunningJobs;
	private List<JobResponse> jobs;
}
