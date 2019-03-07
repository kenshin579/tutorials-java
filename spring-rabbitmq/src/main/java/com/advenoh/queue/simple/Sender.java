//package com.advenoh.queue.simple;
//
//import com.advenoh.config.RabbitMQConfig;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import java.util.concurrent.TimeUnit;
//
//public class Sender {
//	final static String queueName = "test.queue";
//
//	public static void main(String[] args) throws InterruptedException {
//		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//		ctx.register(RabbitMQConfig.class);
//		ctx.refresh();
//
//		System.out.println("---Message is being sent---");
//		RabbitTemplate rabbitTemplate = (RabbitTemplate) ctx.getBean("rabbitTemplate");
//		MyService receiver = (MyService) ctx.getBean("receiver");
//
//		rabbitTemplate.convertAndSend(queueName, "Hello World!");
//		receiver.getCountDownLatch().await(1, TimeUnit.SECONDS);
//
//		ctx.close();
//	}
//}