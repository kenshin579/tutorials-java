package com.concretepage.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;

public class Main {
	final static String queueName = "concretepage";

	public static void main(String[] args) throws InterruptedException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(RabbitMQConfig.class);
		ctx.refresh();
		System.out.println("---Message is being sent---");
		RabbitTemplate rabbitTemplate = (RabbitTemplate) ctx.getBean("rabbitTemplate");
		MessageReceiver receiver = (MessageReceiver) ctx.getBean("receiver");
		rabbitTemplate.convertAndSend(queueName, "Hello World!");
		receiver.getCountDownLatch().await(1, TimeUnit.SECONDS);
		ctx.close();
	}
}