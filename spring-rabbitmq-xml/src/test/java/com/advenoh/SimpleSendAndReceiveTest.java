package kr.pe.advenoh;

import kr.pe.advenoh.queue.simple.Receiver;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-context.xml")
@TestPropertySource("classpath:application.properties")
@Component
public class SimpleSendAndReceiveTest {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private Receiver receiver;

	@Autowired
	Queue queue;

	/**
	 * todo: unit test에서 제대로 receiveMsg가 호출됐는지 체크를 어떻게 할수 있나?
	 *
	 * @throws Exception
	 */
	@Test
	public void test_send_and_receive_hello_world() throws Exception {
		rabbitTemplate.convertAndSend(queue.getName(), "Hello from RabbitMQ!");
		receiver.getCountDownLatch().await(10000, TimeUnit.MILLISECONDS);
	}
}