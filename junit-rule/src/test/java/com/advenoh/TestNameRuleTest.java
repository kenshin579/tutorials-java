package kr.pe.advenoh;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static org.junit.Assert.assertEquals;

public class TestNameRuleTest {
	@Rule
	public TestName name = new TestName();

	@Test
	public void 테스트1_이름입니다() {
		assertEquals("테스트1_이름입니다", name.getMethodName());
	}

	@Test
	public void 테스트2_이름입니다() {
		assertEquals("테스트2_이름입니다", name.getMethodName());
	}
}
