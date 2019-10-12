package com.advenoh.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j
public class CronJob2 extends QuartzJobBean implements InterruptableJob {
    private volatile boolean isJobInterrupted = false;
    private int MAX_SLEEP_IN_SECONDS = 5;

    private volatile Thread currThread;

    @Override
    public void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobKey jobKey = context.getJobDetail().getKey();
        if (!isJobInterrupted) {
            currThread = Thread.currentThread();
            log.info("============================================================================");
            log.info("CronJob started :: sleep : {} jobKey : {} - {}", MAX_SLEEP_IN_SECONDS, jobKey, currThread.getName());

            IntStream.range(0, 3).forEach(i -> {
                log.info("CronJob Counting : {} = {}", i, currThread.getName());
                try {
                    TimeUnit.SECONDS.sleep(MAX_SLEEP_IN_SECONDS);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            });
            log.info("CronJob ended :: jobKey : {} - {}", jobKey, currThread.getName());
            log.info("============================================================================");
        }
    }

    @Override
    public void interrupt() {
        isJobInterrupted = true;
        if (currThread != null) {
            log.info("interrupting - {}", currThread.getName());
            currThread.interrupt();
        }
    }
}
