package com.advenoh.dto.scheduler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class StatsResponse {
	private int numOfAllJobs;
	private int numOfGroups;
	private int numOfRunningJobs;
}
