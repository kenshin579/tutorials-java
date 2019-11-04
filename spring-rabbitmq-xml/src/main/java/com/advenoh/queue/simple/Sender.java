package com.advenoh.queue.simple;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class Sender {
	final static String queueName = "test.queue";

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
		AmqpTemplate aTemplate = (AmqpTemplate) ctx.getBean("tutorialTemplate");// getting a reference to the sender bean
		//		System.out.println("---Message is being sent---");
		//		RabbitTemplate rabbitTemplate = (RabbitTemplate) ctx.getBean("rabbitTemplate");
		//		Receiver receiver = (Receiver) ctx.getBean("receiver");
		//
		//		rabbitTemplate.convertAndSend(queueName, "Hello World!");
		//		receiver.getCountDownLatch().await(1, TimeUnit.SECONDS);
		aTemplate.convertAndSend("my.routingkey.1", "Message # " + 1 + " on " + new Date());// send
		//		ctx.close();
	}
}