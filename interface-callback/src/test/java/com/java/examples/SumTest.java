package com.java.examples;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class SumTest {
	@Test
	public void test_callback() {
		Sum total = new Sum();

		OnMaxNumberCb callback = (number, exceed) -> System.out.println("Current sum is " + number + " and exceeds " + exceed);

		total.setMaxNumber(50);
		total.setOnMaxNumberCb(callback);

		for (int i = 0; i <= 11; i++) {
			total.addNumber(i);
		}

		System.out.println("Total is " + total.getTotal());
	}
}