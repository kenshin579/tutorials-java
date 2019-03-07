package com.advenoh.queue.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {
	//메시지를 처리한다.
	public void handleMessage(Object message) {
		log.info("message: {}", message);
	}
}
