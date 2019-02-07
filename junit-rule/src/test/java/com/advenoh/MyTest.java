package com.advenoh;

import org.junit.Rule;
import org.junit.Test;

public class MyTest {
	@Rule
	public MyTestRule myTestRule = new MyTestRule();

	@Test
	public void testSanity() throws Exception {
		System.out.println("Test is running…");
	}
}