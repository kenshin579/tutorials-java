//package com.advenoh.controller;
//
//import com.advenoh.job.SimpleJob;
//import com.advenoh.service.JobHistoryService;
//import com.advenoh.service.ScheduleService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.*;
//import static org.mockito.ArgumentMatchers.anyObject;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.verify;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@Slf4j
//@RunWith(SpringRunner.class)
//@WebMvcTest(JobHistoryController.class)
//public class JobHistoryControllerTest {
//    private final String BASE_PATH = "/scheduler";
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private JobHistoryService jobHistoryService;
//
//    @Test
//    public void getAllHistoryJobs() {
//        given(jobHistoryService.getAllJobs(anyObject()).;
//        given(scheduleService.isJobExists(anyObject())).willReturn(false);
//
//        mvc.perform(post(BASE_PATH + "/job")
//                .param("jobName", "job1")
//                .param("jobGroup", "testGroup"))
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.success", is(true)));
//
//        verify(scheduleService).addJob(anyObject(), eq(SimpleJob.class));
//        verify(scheduleService).isJobExists(anyObject());
//    }
//}