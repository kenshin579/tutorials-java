package com.jenkov.tutorials.parameterAnnotation;

public class TheClass {
    public static void doSomethingElse(
            @MyAnnotation(name = "aName", value = "aValue") String parameter) {
    }
}