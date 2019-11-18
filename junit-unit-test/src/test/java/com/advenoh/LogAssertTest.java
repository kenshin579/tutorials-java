package com.advenoh;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.advenoh.util.LoggerTestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@Slf4j
public class LogAssertTest {
    SomeService someService;

    @Before
    public void setUp() throws Exception {
        someService = new SomeService();
    }

    @Test
    public void requestJobId() throws JsonProcessingException {
        String jobId = "12342";

        ListAppender<ILoggingEvent> listAppender = LoggerTestUtil.getListAppenderForClass(SomeService.class);

        someService.requestJobId(jobId);

        List<ILoggingEvent> logsList = listAppender.list;
        log.info("[FRANK] logsList : {}", new ObjectMapper().writeValueAsString(logsList));
        assertThat(logsList.get(0).getMessage()).contains("[servicedebug] error occurred : jobId : ");
    }

    @Test
    public void test_add() {
        AddService addService = new AddService();
        int result = addService.add(3, 5);
        assertThat(result).isEqualTo(8);
    }

    @Test
    public void test_print() {
        AddService mockAddService = spy(AddService.class);

        mockAddService.print(3, 5);

        verify(mockAddService).add(3, 5);
    }
}
