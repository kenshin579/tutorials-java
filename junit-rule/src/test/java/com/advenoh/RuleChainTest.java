package com.advenoh;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;

public class RuleChainTest {
	@Rule
	public TestRule chain = RuleChain
			.outerRule(new LoggingRule("outer rule"))
			.around(new LoggingRule("middle rule"))
			.around(new LoggingRule("inner rule"));

	@Test
	public void test() {
	}

}
