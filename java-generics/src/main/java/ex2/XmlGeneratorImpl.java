package ex2;

/**
 * https://stackoverflow.com/questions/2240646/how-to-pass-a-type-as-a-method-parameter-in-java/6144310
 *
 * @param <T>
 */
public class XmlGeneratorImpl<T> implements XmlGenerator<T> {

	private static final int DEFAULT_CAPACITY = 1024;
	private Class<T> valueType;

	public XmlGeneratorImpl(Class<T> clazz) {
		this.valueType = clazz;
	}

	public static void main(String[] args) {
		Integer x = 42;
		String y = "foobar";

		XmlGenerator<Integer> intXmlGenerator = new XmlGeneratorImpl<>(Integer.class);
		XmlGenerator<String> stringXmlGenerator = new XmlGeneratorImpl<>(String.class);

		System.out.println("integer: " + intXmlGenerator.getXml(x));
		System.out.println("string : " + stringXmlGenerator.getXml(y));
	}

	public String getXml(T value) {
		StringBuilder builder = new StringBuilder(DEFAULT_CAPACITY);

		appendTag(builder);
		builder.append(value);
		appendTag(builder, false);

		return builder.toString();
	}

	@Override public String setXml(T value) {
		return null;
	}

	private void appendTag(StringBuilder builder) {
		this.appendTag(builder, false);
	}

	private void appendTag(StringBuilder builder, boolean isClosing) {
		String valueTypeName = valueType.getName();
		builder.append("<").append(valueTypeName);
		if (isClosing) {
			builder.append("/");
		}
		builder.append(">");
	}
}
