package com.advenoh;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Child extends Parent {

	public void methodToTest() {
		//code
		log.info("methodToTest");
		badMethod();
		//code
	}

	@Override public int anotherMethod(int x) {
		return 0;
	}
}
