package com.advenoh.queue.simple;

import kr.pe.advenoh.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;

public class Sender {
	final static String queueName = "test.queue";

	public static void main(String[] args) throws InterruptedException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(RabbitMQConfig.class);
		ctx.refresh();

		System.out.println("---Message is being sent---");
		RabbitTemplate rabbitTemplate = (RabbitTemplate) ctx.getBean("rabbitTemplate");
		Receiver receiver = (Receiver) ctx.getBean("receiver");

		rabbitTemplate.convertAndSend(queueName, "Hello World!");
		receiver.getCountDownLatch().await(3, TimeUnit.SECONDS);

		ctx.close();
	}
}