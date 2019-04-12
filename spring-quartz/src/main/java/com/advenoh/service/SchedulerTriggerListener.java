package com.advenoh.service;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SchedulerTriggerListener implements TriggerListener {

	@Override
	public String getName() {
		return "globalTrigger";
	}

	@Override
	public void triggerFired(Trigger trigger, JobExecutionContext context) {
		String triggerName = context.getJobDetail().getKey().toString();
		log.info("triggerFired");
		log.info("trigger : {} is fired", triggerName);
	}

	@Override
	public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
		log.info("TriggerListner.vetoJobExecution()");
		return false;
	}

	@Override
	public void triggerMisfired(Trigger trigger) {
		log.info("TriggerListner.triggerMisfired()");
		String jobName = trigger.getJobKey().getName();
		log.info("Job name: {},  trigger: {}  misfired at {}", jobName, trigger.getJobKey(), trigger.getStartTime());

	}

	@Override
	public void triggerComplete(Trigger trigger, JobExecutionContext context, CompletedExecutionInstruction triggerInstructionCode) {
		log.info("TriggerListner.triggerComplete()");
		String jobName = trigger.getJobKey().getName();
		log.info("Job name: {},  trigger: {}  completed at {}", jobName, trigger.getJobKey(), trigger.getStartTime());
	}
}
