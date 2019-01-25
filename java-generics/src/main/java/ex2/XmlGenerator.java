package ex2;

public interface XmlGenerator<T> {
	String getXml(T value);

	String setXml(T value);
}
