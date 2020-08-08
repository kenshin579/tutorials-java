package kr.pe.advenoh.controller;

import com.jayway.jsonpath.JsonPath;
import kr.pe.advenoh.exception.StudentExceptionCode;
import kr.pe.advenoh.utils.TestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest implements TestConfig {
    private final String PATH = "/api/student";
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    void addStudent_getStudent_deleteStudent() throws Exception {
        //학생 생성
        MvcResult mvcResult = this.mockMvc.perform(post(PATH)
                .param("name", name)
                .param("mobileNumber", mobileNumber)
                .param("address", address))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.mobileNumber", is(mobileNumber)))
                .andExpect(jsonPath("$.address", is(address)))
                .andReturn();

        //학생 조회
        Integer studentId = JsonPath.parse(mvcResult.getResponse().getContentAsString()).read("$.id");
        log.info("studentId : {}", studentId);

        this.mockMvc.perform(get(PATH + "/{studentId}", studentId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(studentId)));

        //학생 삭제
        this.mockMvc.perform(delete(PATH + "/{studentId}", studentId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.succeed", is(true)));
    }

    @Test
    void getStudents() throws Exception {
        this.mockMvc.perform(get(PATH))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    //todo: 작업이 필요함
    @Test
    @Transactional
    void addStudent_request_값이_잘못된_경우() throws Exception {
        this.mockMvc.perform(post(PATH)
                .param("mobileNumber", mobileNumber)
                .param("address", address))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void getStudent_NOT_FOUND_에외처리_확인() throws Exception {
        this.mockMvc.perform(get(PATH + "/{studentId}", Integer.MAX_VALUE))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(StudentExceptionCode.STUDENT_NOT_FOUND.getMessage())));
    }
}