package com.jenkov.tutorials.methodAnnotation;

public class TheClass {
    @MyAnnotation(name = "doThisMethod", value = "Hello World")
    public void doThis() {
    }

    @MyAnnotation(name = "doThatMethod")
    public void doThat() {
    }
}