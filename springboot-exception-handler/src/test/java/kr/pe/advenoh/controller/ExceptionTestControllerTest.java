package kr.pe.advenoh.controller;

import kr.pe.advenoh.utils.SpringMockMvcTestSupport;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ExceptionTestControllerTest extends SpringMockMvcTestSupport {
    private final String PATH = "/api/exception2";

    @Test
    void throwCustomException() throws Exception {
        this.mockMvc.perform(get(PATH + "/supportInfoException2"))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", is("Exception occurred")));

    }
}