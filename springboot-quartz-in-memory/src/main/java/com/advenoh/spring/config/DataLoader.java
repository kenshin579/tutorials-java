package com.advenoh.spring.config;

import com.advenoh.model.JobHistoryLog;
import com.advenoh.model.JobType;
import com.advenoh.repository.JobHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Random;
import java.util.stream.IntStream;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private JobHistoryRepository jobHistoryRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        IntStream.range(1, 5).forEach(i -> {
            String jobName = "job" + i;
            String jobGroup = "group" + i;

            JobHistoryLog jobHistoryLog = new JobHistoryLog(jobName, jobGroup, getRandomJob(), Instant.now(), Instant.now());
            jobHistoryRepository.save(jobHistoryLog);

        });
    }

    JobType getRandomJob() {
        return JobType.values()[new Random().nextInt(JobType.values().length)];
    }
}
