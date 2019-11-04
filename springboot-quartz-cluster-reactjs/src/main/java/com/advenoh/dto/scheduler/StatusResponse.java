package com.advenoh.dto.scheduler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class StatusResponse {
	private StatsResponse stats;
	private List<JobResponse> jobs;
}
