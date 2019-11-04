package com.advenoh;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class LogAssertTest {
	SomeService someService;

	@Before
	public void setUp() throws Exception {
		someService = new SomeService();
	}

	@Test
	public void requestJobId() {
		String jobId = "12342";

		Logger logger = (Logger) LoggerFactory.getLogger(SomeService.class);
		ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
		listAppender.start();

		logger.addAppender(listAppender);

		someService.requestJobId(jobId);

		List<ILoggingEvent> logsList = listAppender.list;
		assertThat(logsList.get(0).getMessage()).contains("[servicedebug] error occurred : jobId : ");
	}
}
