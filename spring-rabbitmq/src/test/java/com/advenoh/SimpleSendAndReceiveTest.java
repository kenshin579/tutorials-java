//package com.advenoh;
//
//import com.advenoh.config.RabbitMQConfig;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@Slf4j
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = RabbitMQConfig.class)
//@TestPropertySource("classpath:application.properties")
//@Component
//public class SimpleSendAndReceiveTest {
//
//	@Autowired
//	private RabbitTemplate rabbitTemplate;
//
//	//	@Value("${rabbitmq.queue.name}")
//	//	private String queueName;
//
//	//	private final Queue queue;
//
//	@Autowired
//	private Queue queue;
//
//	//	@Autowired
//	//	MyService receiver;
//
//	@Test
//	public void test_() throws InterruptedException {
//		//		log.info("queueName: {}", );
//		String queueName = "test.queue";
//
//		rabbitTemplate.convertAndSend(queue.getName(), "Hello World!");
//		Thread.sleep(3000);
//		//		receiver.getCountDownLatch().await(3, TimeUnit.SECONDS);
//
//	}
//}
