package kr.pe.advenoh;

public abstract class AbstractClazz3 {
	String test = null;

	public String sayHello() {
		return "Hello !";
	}

	protected String getParentName() {
		//null이 될수밖에 없음...
		return test.substring(3) + " " + sayHello();
	}

	public abstract String testMethod();
}
