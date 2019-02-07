package com.advenoh;

import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Verifier;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@Slf4j
public class VerifierRuleTest {
	private List<String> errorLog = new ArrayList<>();

	@Rule
	public Verifier verifier = new Verifier() {
		//After each method perform this check
		@Override public void verify() {
			assertTrue("Error Log is not Empty!", errorLog.isEmpty());
		}
	};

	@Test
	public void testWritesErrorLog() {
		errorLog.add("There is an error!");
	}

	@Test
	public void anotherTest() {
		log.info("test");
	}
}
