package com.advenoh;

public abstract class AbstractClazz2 {
	public String sayHello() {
		return "Hello " + fetchName() + closingSymbol();
	}

	private String closingSymbol() {
		return "!";
	}

	protected abstract String fetchName();
}
