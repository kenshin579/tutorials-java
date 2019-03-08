package com.advenoh;

import com.advenoh.config.RabbitMQConfig;
import com.advenoh.queue.simple.Receiver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RabbitMQConfig.class)
public class RabbitMQTest {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private Receiver receiver;

	@Autowired
	Queue queue;

	@Test
	public void test_send_and_receive_hello_world() throws Exception {
		rabbitTemplate.convertAndSend(queue.getName(), "Hello from RabbitMQ!");
		receiver.getCountDownLatch().await(10000, TimeUnit.MILLISECONDS);
	}
}
