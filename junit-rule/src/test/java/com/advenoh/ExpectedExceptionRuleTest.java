package kr.pe.advenoh;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExpectedExceptionRuleTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void IllegalArgumentException_예외_발생_확인() {
		exception.expect(IllegalArgumentException.class);
		throw new IllegalArgumentException();
	}

	@Test
	public void RuntimeException_예외_발생시_메시지도_같이_확인() {
		exception.expect(RuntimeException.class);
		exception.expectMessage("failed!");
		throw new RuntimeException("failed!");
	}
}
