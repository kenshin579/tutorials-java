package com.java.examples;

import lombok.extern.slf4j.Slf4j;

/**
 * http://rockdrumy.tistory.com/1058
 */
@Slf4j
public class Sum {
	private int number = 0;
	private int maxNumber = 0;
	private OnMaxNumberCb myCallback;

	public void setOnMaxNumberCb(OnMaxNumberCb myCallback) {
		this.myCallback = myCallback;
	}

	public void setMaxNumber(int max) {
		maxNumber = max;
	}

	public int addNumber(int adder) {
		number = number + adder;

		if (myCallback != null) {
			if (number > maxNumber) {
				System.out.println("callback call...");
				myCallback.onMaxNumber(number, number - maxNumber);
			}
		}
		return number;
	}

	public int getTotal() {
		return number;
	}
}
