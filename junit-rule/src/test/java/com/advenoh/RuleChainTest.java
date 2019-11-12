package com.advenoh;

import com.advenoh.rules.LoggingRule;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;

@Slf4j
public class RuleChainTest {
	@Rule
	public TestRule chain = RuleChain
			.outerRule(new LoggingRule("외부 규칙"))
			.around(new LoggingRule("중간 규칙"))
			.around(new LoggingRule("내부 규칙"));

	@Test
	public void test() {
	}
}
