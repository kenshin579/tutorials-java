package kr.pe.advenoh.controller;

import kr.pe.advenoh.dto.scheduler.StatusResponse;
import kr.pe.advenoh.job.SimpleJob;
import kr.pe.advenoh.service.ScheduleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ScheduleController.class)
public class ScheduleControllerTest {
    private final String BASE_PATH = "/scheduler";
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ScheduleService scheduleService;

    @Test
    public void addScheduleJob_simpleJob() throws Exception {
        given(scheduleService.addJob(anyObject(), eq(SimpleJob.class))).willReturn(true);
        given(scheduleService.isJobExists(anyObject())).willReturn(false);

        mvc.perform(post(BASE_PATH + "/job")
                .param("jobName", "job1")
                .param("jobGroup", "testGroup"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success", is(true)));

        verify(scheduleService).addJob(anyObject(), eq(SimpleJob.class));
        verify(scheduleService).isJobExists(anyObject());
    }

    @Test
    public void deleteScheduleJob() throws Exception {
        given(scheduleService.deleteJob(anyObject())).willReturn(true);
        given(scheduleService.isJobExists(anyObject())).willReturn(true);
        given(scheduleService.isJobRunning(anyObject())).willReturn(false);

        mvc.perform(delete(BASE_PATH + "/job")
                .param("jobName", "job1")
                .param("jobGroup", "testGroup"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)));

        verify(scheduleService).deleteJob(anyObject());
        verify(scheduleService).isJobExists(anyObject());
        verify(scheduleService).isJobRunning(anyObject());
    }

    @Test
    public void updateScheduleJob() throws Exception {
        given(scheduleService.updateJob(anyObject())).willReturn(true);
        given(scheduleService.isJobExists(anyObject())).willReturn(true);

        mvc.perform(put(BASE_PATH + "/job/update")
                .param("jobName", "job1")
                .param("jobGroup", "testGroup"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)));

        verify(scheduleService).updateJob(anyObject());
        verify(scheduleService).isJobExists(anyObject());
    }

    @Test
    public void getAllJobs() throws Exception {
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setNumOfAllJobs(1);

        given(scheduleService.getAllJobs()).willReturn(statusResponse);

        mvc.perform(get(BASE_PATH + "/jobs"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numOfAllJobs", is(1)));

        verify(scheduleService).getAllJobs();
    }

    @Test
    public void pauseJob() throws Exception {
        given(scheduleService.pauseJob(anyObject())).willReturn(true);
        given(scheduleService.isJobExists(anyObject())).willReturn(true);
        given(scheduleService.isJobRunning(anyObject())).willReturn(false);

        mvc.perform(put(BASE_PATH + "/job/pause")
                .param("jobName", "job1")
                .param("jobGroup", "testGroup"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)));

        verify(scheduleService).pauseJob(anyObject());
        verify(scheduleService).isJobExists(anyObject());
        verify(scheduleService).isJobRunning(anyObject());
    }

    @Test
    public void resumeJob() throws Exception {
        given(scheduleService.resumeJob(anyObject())).willReturn(true);
        given(scheduleService.isJobExists(anyObject())).willReturn(true);
        given(scheduleService.getJobState(anyObject())).willReturn("PAUSED");

        mvc.perform(put(BASE_PATH + "/job/resume")
                .param("jobName", "job1")
                .param("jobGroup", "testGroup"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)));

        verify(scheduleService).resumeJob(anyObject());
        verify(scheduleService).isJobExists(anyObject());
        verify(scheduleService).getJobState(anyObject());
    }
}