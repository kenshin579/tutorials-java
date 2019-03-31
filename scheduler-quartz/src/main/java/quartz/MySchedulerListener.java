package quartz;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerListener;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

public class MySchedulerListener implements SchedulerListener {

	private final Scheduler scheduler;

	public MySchedulerListener(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public void jobScheduled(Trigger trigger) {
		System.out.println("Job scheduled: " + trigger.getKey().getName());
	}

	public void jobUnscheduled(TriggerKey triggerKey) {
		System.out.println("Job Unscheduled: " + triggerKey.getName());
	}

	public void triggerFinalized(Trigger trigger) {
		System.out.println("Job trigger finalized: "
				+ trigger.getKey().getName());
	}

	public void triggerPaused(TriggerKey triggerKey) {
		System.out.println("Job trigger paused: " + triggerKey.getName());
	}

	public void triggersPaused(String triggerGroup) {
		System.out.println("Job triggers paused for trigger group: "
				+ triggerGroup);
	}

	public void triggerResumed(TriggerKey triggerKey) {
		System.out.println("Job triggers resumed for trigger: " + triggerKey);
	}

	public void triggersResumed(String triggerGroup) {
		System.out.println("Job triggers resumed for trigger group: "
				+ triggerGroup);
	}

	public void jobAdded(JobDetail jobDetail) {
		System.out.println("Job added: " + jobDetail.getKey().getName());
	}

	public void jobDeleted(JobKey jobKey) {
		System.out.println("Job deleted: " + jobKey.getName());
	}

	public void jobPaused(JobKey jobKey) {
		System.out.println("Jobs paused for job: " + jobKey);
	}

	public void jobsPaused(String jobGroup) {
		System.out.println("Jobs paused for job group: " + jobGroup);
	}

	public void jobResumed(JobKey jobKey) {
		System.out.println("Job resumed: " + jobKey.getName());
	}

	public void jobsResumed(String jobGroup) {
		System.out.println("Jobs resumed for job group: " + jobGroup);
	}

	public void schedulerError(String msg, SchedulerException cause) {
		System.out.println("Scheduler Error: " + cause);
	}

	public void schedulerInStandbyMode() {
		try {
			System.out.println("Scheduler put in standby mode: "
					+ scheduler.getSchedulerName());
		} catch (SchedulerException e) {
			System.out.println("Error getting scheduler name" + e);
		}
	}

	public void schedulerStarted() {
		try {
			System.out.println("Scheduler started: "
					+ scheduler.getSchedulerName());
		} catch (SchedulerException e) {
			System.out.println("Error getting scheduler name" + e);
		}
	}

	public void schedulerShutdown() {
		try {
			System.out.println("Scheduler shutdown: "
					+ scheduler.getSchedulerName());
		} catch (SchedulerException e) {
			System.out.println("Error getting scheduler name" + e);
		}
	}

	public void schedulerShuttingdown() {
		try {
			System.out.println("Scheduler shutting down: "
					+ scheduler.getSchedulerName());
		} catch (SchedulerException e) {
			System.out.println("Error getting scheduler name" + e);
		}
	}

	public void schedulingDataCleared() {
		try {
			System.out.println("Scheduler data cleared: "
					+ scheduler.getSchedulerName());
		} catch (SchedulerException e) {
			System.out.println("Error getting scheduler name" + e);
		}
	}

	public void schedulerStarting() {
		try {
			System.out.println("Scheduler starting: "
					+ scheduler.getSchedulerName());
		} catch (SchedulerException e) {
			System.out.println("Error getting scheduler name" + e);
		}
	}

}