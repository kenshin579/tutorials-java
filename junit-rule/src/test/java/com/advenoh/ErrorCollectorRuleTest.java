package com.advenoh;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;

public class ErrorCollectorRuleTest {
	@Rule
	public ErrorCollector collector = new ErrorCollector();

	@Test
	public void test_첫번째_테스트실행이후에도_실행됨() {
		collector.addError(new Throwable("First Error!"));
		collector.addError(new Throwable("Second Error!"));

		collector.checkThat(5, is(8)); //First Error
		collector.checkThat(5, is(5)); //Passed
		collector.checkThat(5, is(equalTo(9))); //Second Error
	}
}
