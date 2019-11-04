package kr.pe.advenoh.utils;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.impl.JobExecutionContextImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.quartz.spi.TriggerFiredBundle;

import java.util.Date;

@Slf4j
public class TestUtils {
    public static JobExecutionContext createJobExecutionContext(Scheduler scheduler, JobDetail jobDetail, Class clazz) {
        Date now = new Date();
        TriggerFiredBundle firedBundle = new TriggerFiredBundle(jobDetail, new SimpleTriggerImpl(), null, false, now, now, now, now);
        JobExecutionContextImpl jobExecutionContext = null;

        try {
            jobExecutionContext = new JobExecutionContextImpl(scheduler, firedBundle, (Job) clazz.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("error occurred while creating jobContext", e);
        }
        return jobExecutionContext;
    }

}
