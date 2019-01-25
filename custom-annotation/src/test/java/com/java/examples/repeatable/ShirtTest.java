package com.java.examples.repeatable;

import org.junit.Test;

public class ShirtTest {

    /**
     * https://www.javabrahman.com/java-8/java-8-repeating-annotations-tutorial/
     */
    @Test
    public void testRepeableAnnotationUsage() {
        Color[] colorArray = Shirt.class.getAnnotationsByType(Color.class);
        for (Color color : colorArray) {
            System.out.println(color.name());
        }
    }
}