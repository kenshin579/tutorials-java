package com.advenoh;

import com.advenoh.queue.spring.Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-context.xml")
public class SpringTest {
	@Test
	public void test_() throws InterruptedException {
		Producer producer = new Producer();
		producer.sendMessage("test!!!");
	}
}
