package com.advenoh.util;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.slf4j.LoggerFactory;

public class LoggerTestUtil {
	public static ListAppender<ILoggingEvent> getListAppenderForClass(Class clazz) {
		Logger logger = (Logger) LoggerFactory.getLogger(clazz);

		ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
		listAppender.start(); //기록을 시작한다

		logger.addAppender(listAppender); //로거에 ListAppender를 추가하여 발생하는 로그를 List에 저장하도록 한다

		return listAppender;
	}
}
