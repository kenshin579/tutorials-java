package com.advenoh.controller;

import com.advenoh.service.JobHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * https://examples.javacodegeeks.com/enterprise-java/spring/boot/spring-boot-jpa-mysql-example/
 *
 */
@Slf4j
@RestController
@RequestMapping("history")
public class JobHistoryController {
    @Autowired
    private JobHistoryService jobHistoryService;

//    @GetMapping
//    public List<JobHistoryLog> getAllJobHistoryAfterCreatedDate(@RequestParam createdAfter) {
//        return jobHistoryService.findAllByCreatedAtAfter()
//    }


}
