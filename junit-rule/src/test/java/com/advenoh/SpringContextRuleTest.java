package com.advenoh;

import com.advenoh.rules.SpringContextRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class SpringContextRuleTest {

	@Rule
	public TestRule contextRule = new SpringContextRule(
			new String[] { "testContext.xml" }, this);

	@Autowired
	public String bar;

	@Test
	public void testBaz() throws Exception {
		assertThat(bar).isEqualTo("bar");
	}
}
