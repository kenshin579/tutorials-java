package kr.pe.advenoh.controller;

import kr.pe.advenoh.utils.SpringMockMvcTestSupport;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ExceptionTestControllerTestRest extends SpringMockMvcTestSupport {
    private final String PATH = "/api/exception";

    @Test
    void throwCustomException() throws Exception {
        this.mockMvc.perform(get(PATH + "/supportInfoException"))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", is("Exception occurred")));

    }
}