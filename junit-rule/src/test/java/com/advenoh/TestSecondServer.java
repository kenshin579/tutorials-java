package com.advenoh;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class TestSecondServer {
	@Test
	public void test() throws Exception {
		log.info("{}", this.getClass().getSimpleName());
	}
}