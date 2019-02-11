package com.advenoh;

import org.junit.Rule;
import org.junit.Test;

public class CustomRuleTest {

	@Rule
	public LoggingRule rule = new LoggingRule("custom rule");

	@Test
	public void test() {
		System.out.println("test 실행");
	}
}
