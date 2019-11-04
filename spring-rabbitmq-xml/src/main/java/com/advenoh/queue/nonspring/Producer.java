package kr.pe.advenoh.queue.nonspring;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class Producer {
	public static void main(final String[] args) {
		//RabbitMQ 연결
		CachingConnectionFactory cf
				= new CachingConnectionFactory("127.0.0.1", 5672);
		cf.setUsername("guest");
		cf.setPassword("guest");

		//메시지 보내기
		RabbitTemplate template = new RabbitTemplate(cf);
		template.setExchange("amq.direct");
		template.setQueue("myQueue");
		template.convertAndSend("foo.bar", "Hello, world!");
		cf.destroy();
	}
}


