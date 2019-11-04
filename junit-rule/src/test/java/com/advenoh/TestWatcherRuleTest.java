package com.advenoh;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.MethodSorters;
import org.junit.runners.model.Statement;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestWatcherRuleTest {
	private static String watchedLog = "\n";

	@Rule
	public TestRule watchman = new TestWatcher() {
		@Override
		public Statement apply(Statement base, Description description) {
			return super.apply(base, description);
		}

		@Override
		protected void succeeded(Description description) {
			watchedLog += description.getDisplayName() + "  success!\n";
			System.out.println(String.format("성공!\nWatchlog: %s", watchedLog));
		}

		@Override
		protected void failed(Throwable e, Description description) {
			watchedLog += description.getDisplayName() + " " + e.getClass().getSimpleName() + "\n";
			System.out.println(String.format("실패!\nWatchlog: %s", watchedLog));
		}

		@Override
		protected void starting(Description description) {
			super.starting(description);
			System.out.println(String.format("==================== 테스트 시작! ==================== \nWatchlog: %s", watchedLog));
		}

		@Override
		protected void finished(Description description) {
			super.finished(description);
			System.out.println(String.format("==================== 테스트 끝! ==================== \nWatchlog: %s", watchedLog));
		}
	};

	@Test
	public void T1_succeeds() {
		Assert.assertEquals(5, 5);
	}

	@Test
	public void T2_succeeds2() {
		Assert.assertEquals(2, 2);
	}

	@Test
	public void T3_fails() {
		Assert.assertEquals(3, 5);
	}

}
