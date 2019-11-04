package kr.pe.advenoh.controller;

import kr.pe.advenoh.model.JobStatus;
import kr.pe.advenoh.service.JobHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@WebMvcTest(JobHistoryController.class)
public class JobHistoryControllerTest {
    private final String BASE_PATH = "/history";
    @Autowired
    private MockMvc mvc;

    @MockBean
    private JobHistoryService jobHistoryService;

    @Test
    public void getAllHistoryJobs() throws Exception {
        List<JobStatus> jobStatusList = new ArrayList<>();
        Page<JobStatus> jobStatusPage = new PageImpl<>(jobStatusList);
        given(jobHistoryService.getAllJobs(anyObject())).willReturn(jobStatusPage);

        this.mvc.perform(get(BASE_PATH + "/jobs"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());

        verify(jobHistoryService).getAllJobs(anyObject());
    }
}