package com.advenoh;

import com.advenoh.rules.SpringContextRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class SpringContextRuleTest {

	@Rule
	public TestRule contextRule = new SpringContextRule(
			new String[] { "testContext.xml" }, this);

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@Autowired
	public String bar;

	@Mock
	public List baz;

	@Test
	public void testBar() throws Exception {
		assertThat(bar).isEqualTo("bar");
	}

	@Test
	public void testBaz() throws Exception {
		when(baz.size()).thenReturn(2);
		assertThat(baz.size()).isEqualTo(2);
	}
}
