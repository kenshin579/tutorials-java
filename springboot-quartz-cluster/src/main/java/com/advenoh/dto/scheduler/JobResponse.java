package kr.pe.advenoh.dto.scheduler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JobResponse {
	private String jobName;
	private String groupName;
	private String jobStatus;
	private String scheduleTime;
	private String lastFiredTime;
	private String nextFireTime;
}
