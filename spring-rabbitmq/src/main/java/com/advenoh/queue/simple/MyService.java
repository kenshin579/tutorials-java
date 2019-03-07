package com.advenoh.queue.simple;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

//@RabbitListener(queues = "test.queue")
@Component
public class MyService {
	private CountDownLatch countDownLatch = new CountDownLatch(1);

	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(value = "test.queue", durable = "false"),
			exchange = @Exchange(value = "test.exchange"))
	)
	public void receiveMsg(String message) {
		System.out.println("Message Received: " + message);
		countDownLatch.countDown();
	}

	public CountDownLatch getCountDownLatch() {
		return countDownLatch;
	}
}
 