package com.advenoh.spring;

import com.advenoh.model.JobType;
import com.advenoh.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private ScheduleService scheduleService;

//    @Autowired
//    private JobHistoryRepository jobHistoryRepository;
//
//    @Autowired
//    private JobStatusRepository jobStatusRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        //simple job 생성
//        JobRequest jobRequest = new JobRequest();
//        jobRequest.setJobName("simpleJob");
//        jobRequest.setStartDateAt(LocalDateTime.now());
//        jobRequest.setRepeatCount(50);
//        jobRequest.setRepeatIntervalInSeconds(30);
//        scheduleService.addJob(jobRequest, SimpleJob.class);
//
//        //cron job 생성
//        JobDataMap jobDataMap = new JobDataMap();
//        jobDataMap.put("jobId", "123456789");
//        jobRequest = new JobRequest();
//        jobRequest.setJobName("cronJob1");
//        jobRequest.setCronExpression("0 * * ? * *"); //every min
//        jobRequest.setJobDataMap(jobDataMap);
//        scheduleService.addJob(jobRequest, CronJob.class);
//
//        jobRequest = new JobRequest();
//        jobRequest.setJobName("cronJob2");
//        jobRequest.setCronExpression("0 */5 * ? * *"); //every 5 min
//        scheduleService.addJob(jobRequest, CronJob2.class);

//        IntStream.range(1, 5).forEach(i -> {
//            String jobName = "job" + i;
//            String jobGroup = "group" + i;
//
//            //team
//            JobHistoryLog jobHistoryLog = new JobHistoryLog(jobName, jobGroup, getRandomJob(), Instant.now(), Instant.now());
//            //member
//            JobStatusLog jobStatusLog1 = new JobStatusLog("RUNNING", Instant.now());
//            JobStatusLog jobStatusLog2 = new JobStatusLog("SCHEDULED", Instant.now());
//
//            jobStatusLog1.setJobHistoryLog(jobHistoryLog);
//            jobStatusLog2.setJobHistoryLog(jobHistoryLog);
//
//            jobHistoryRepository.save(jobHistoryLog);
//            jobStatusRepository.save(jobStatusLog1);
//            jobStatusRepository.save(jobStatusLog2);
//
//            List<JobHistoryLog> jobHistoryLogList = jobHistoryRepository.findAll();
//            log.info("jobHistoryLogList :{}", jobHistoryLogList);
//        });
    }

    public JobType getRandomJob() {
        return JobType.values()[new Random().nextInt(JobType.values().length)];
    }
}
