//package com.advenoh.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
//
//@Slf4j
//@Configuration
//@PropertySource("classpath:application.properties")
//public class RabbitMQConfig {
//
//	@Value("${rabbitmq.queue.name}")
//	public String queueName;
//
//	@Value("${rabbitmq.exchange.name}")
//	public String exchangeName;
//
//	@Value("${rabbitmq.host}")
//	public String host;
//
//	@Value("${rabbitmq.port}")
//	public String port;
//
//	@Bean
//	public ConnectionFactory connectionFactory() {
//		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
//		return connectionFactory;
//	}
//
//	@Bean
//	public RabbitTemplate rabbitTemplate() {
//		return new RabbitTemplate(connectionFactory());
//	}
//
//	@Bean
//	public Queue myQueue() {
//		return new Queue(queueName);
//	}
//
//	@Bean(name = "rabbitListenerContainerFactory")
//	public SimpleRabbitListenerContainerFactory rabbitListenerContainerlistenerFactory() {
//		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//		factory.setConnectionFactory(connectionFactory());
//		return factory;
//	}
//
//	@Bean
//	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
//		return new PropertySourcesPlaceholderConfigurer();
//	}
//}
