package com.advenoh.spring.config;

import com.advenoh.model.JobHistoryLog;
import com.advenoh.model.JobStatusLog;
import com.advenoh.model.JobType;
import com.advenoh.repository.JobHistoryRepository;
import com.advenoh.repository.JobStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private JobHistoryRepository jobHistoryRepository;

    @Autowired
    private JobStatusRepository jobStatusRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        IntStream.range(1, 5).forEach(i -> {
            String jobName = "job" + i;
            String jobGroup = "group" + i;

            //team
            JobHistoryLog jobHistoryLog = new JobHistoryLog(jobName, jobGroup, getRandomJob(), Instant.now(), Instant.now());
            //member
            JobStatusLog jobStatusLog1 = new JobStatusLog("RUNNING", Instant.now());
            JobStatusLog jobStatusLog2 = new JobStatusLog("SCHEDULED", Instant.now());

            jobStatusLog1.setJobHistoryLog(jobHistoryLog);
            jobStatusLog2.setJobHistoryLog(jobHistoryLog);

            jobHistoryRepository.save(jobHistoryLog);

            List<JobHistoryLog> jobHistoryLogList = jobHistoryRepository.findAll();
            log.info("jobHistoryLogList :{}", jobHistoryLogList);
        });
    }

    JobType getRandomJob() {
        return JobType.values()[new Random().nextInt(JobType.values().length)];
    }
}
