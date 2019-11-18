package com.advenoh.util;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.slf4j.LoggerFactory;

public class LoggerTestUtil {
	public static ListAppender<ILoggingEvent> getListAppenderForClass(Class clazz) {
		Logger logger = (Logger) LoggerFactory.getLogger(clazz);

		ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
		listAppender.start();

		logger.addAppender(listAppender);

		return listAppender;
	}
}
