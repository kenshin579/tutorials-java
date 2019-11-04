package kr.pe.advenoh;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.concurrent.TimeUnit;

public class TimeoutRuleTest {
	@Rule
	public Timeout timeout = Timeout
			.builder()
			.withTimeout(2, TimeUnit.SECONDS)
			.build();

	@Test
	public void test1() {
		while (true) {
		}
	}

	@Test
	public void test2() {
		while (true) {
		}
	}
}
