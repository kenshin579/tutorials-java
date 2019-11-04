package kr.pe.advenoh;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class TestJOB implements Job {
	public void execute(JobExecutionContext context) {
		System.out.println("I am JOB, schdule me with Quartz");
		System.out.println("Send SMS here");
	}
}