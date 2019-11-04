package kr.pe.advenoh;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Parent {

	public Parent() {
	}

	protected void badMethod() {
		//code
		log.info("badMethod");
	}

	// public methods
	public abstract int anotherMethod(int x);

}