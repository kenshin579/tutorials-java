package com.advenoh.impl;

import com.advenoh.Parent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChildImpl extends Parent {

	public int anotherMethod(int x) {
		log.info("anotherMethod...");
		return x;
	}

	public int callingParentMethod(int x) {
		log.info("callingParentMethod...");
		badMethod();
		return x;
	}
}
