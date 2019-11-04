package kr.pe.advenoh;

public abstract class AbstractClazz {
	public String sayHello() {
		return "Hello " + fetchName() + "!";
	}

	protected abstract String fetchName();
}
