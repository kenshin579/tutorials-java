package com.advenoh.controller;

import com.advenoh.dto.JobStatusResponse;
import com.advenoh.service.ScheduleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(ScheduleController.class)
public class ScheduleControllerTest {
    private final String BASE_PATH = "/scheduler";
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ScheduleService scheduleService;

//    @Test
//    public void whenPostEmployee_thenCreateEmployee() throws Exception {
//        Employee alex = new Employee("alex");
//        given(service.save(Mockito.any())).willReturn(alex);
//
//        mvc.perform(post("/api/employees").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(alex))).andExpect(status().isCreated()).andExpect(jsonPath("$.name", is("alex")));
//        verify(service, VerificationModeFactory.times(1)).save(Mockito.any());
//        reset(service);
//    }

    @Test
    public void addScheduleJob() {

    }

    @Test
    public void deleteScheduleJob() {
    }

    @Test
    public void getAllJobs() throws Exception {
        JobStatusResponse jobStatusResponse = new JobStatusResponse();
        jobStatusResponse.setNumOfAllJobs(1);

        given(scheduleService.getAllJobs()).willReturn(jobStatusResponse);

        mvc.perform(get(BASE_PATH + "/jobs"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numOfAllJobs", is(1)));

        verify(scheduleService).getAllJobs();
    }

    @Test
    public void pauseJob() {
    }

    @Test
    public void resumeJob() {
    }
}