package kr.pe.advenoh.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
public abstract class SpringMockMvcTestSupport extends SpringBootTestSupport {
    @Autowired
    protected MockMvc mockMvc;
}
