package com.advenoh;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExpectedExceptionRuleTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void throwsNullPointerException() {
		thrown.expect(RuntimeException.class);
		throw new NullPointerException();
	}

	@Test
	public void throwsRuntimeExceptionWithMessage() {
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("failed!");
		throw new RuntimeException("failed!");
	}
}
