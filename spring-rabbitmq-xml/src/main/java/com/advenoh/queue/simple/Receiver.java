package kr.pe.advenoh.queue.simple;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {


	private CountDownLatch countDownLatch = new CountDownLatch(1);

	public void receiveMsg(String message) {
		System.out.println("Message Received: " + message);
		countDownLatch.countDown();
	}

	public CountDownLatch getCountDownLatch() {
		return countDownLatch;
	}
}
 