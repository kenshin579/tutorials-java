package com.advenoh;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class MyTestRule implements TestRule {
	public Statement apply(final Statement base, Description description) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				System.out.println("Before-the-test");
				try {
					base.evaluate();
				} finally {
					System.out.println("After-the-test");
				}
			}
		};
	}
}