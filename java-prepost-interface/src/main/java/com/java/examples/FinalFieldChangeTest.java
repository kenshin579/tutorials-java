package com.java.examples;

import java.lang.reflect.Field;

/**
 * https://stackoverflow.com/questions/1615163/modifying-final-fields-in-java
 */
public class FinalFieldChangeTest {
    private final int primitiveInt = 42;
    private final Integer wrappedInt = 42;
    private final String stringValue = "42";

    public int getPrimitiveInt() {
        return this.primitiveInt;
    }

    public int getWrappedInt() {
        return this.wrappedInt;
    }

    public String getStringValue() {
        return this.stringValue;
    }

    public void changeField(String name, Object value) throws IllegalAccessException, NoSuchFieldException {
        Field field = FinalFieldChangeTest.class.getDeclaredField(name);
        field.setAccessible(true);
        field.set(this, value);
        System.out.println("reflection: " + name + " = " + field.get(this));
    }

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        FinalFieldChangeTest test = new FinalFieldChangeTest();

        System.out.println("direct: primitiveInt = " + test.getPrimitiveInt());
        test.changeField("primitiveInt", 84);
        System.out.println("direct: primitiveInt = " + test.getPrimitiveInt());

        System.out.println();

        System.out.println("direct: wrappedInt = " + test.getWrappedInt());
        test.changeField("wrappedInt", 84);
        System.out.println("direct: wrappedInt = " + test.getWrappedInt());

        System.out.println();

        System.out.println("direct: stringValue = " + test.getStringValue());
        test.changeField("stringValue", "84");
        System.out.println("direct: stringValue = " + test.getStringValue());
    }
}