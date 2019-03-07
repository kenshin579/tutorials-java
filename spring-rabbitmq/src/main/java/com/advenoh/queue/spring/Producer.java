package com.advenoh.queue.spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendMessage(Object message) {
		//메시지를 보낸다.
		rabbitTemplate.convertAndSend(message);
	}
}
