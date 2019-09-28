package com.advenoh.controller;

import com.advenoh.model.JobHistory;
import com.advenoh.service.JobHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/history")
public class JobHistoryController {
    @Autowired
    private JobHistoryService jobHistoryService;

    @GetMapping("/jobs")
    public Page<JobHistory> getAllHistoryJobs(Pageable pageable) {
        Page<JobHistory> allJobs = jobHistoryService.getAllJobs(pageable);
        log.info("allJobs : {}", allJobs);
        return allJobs;
    }
}
