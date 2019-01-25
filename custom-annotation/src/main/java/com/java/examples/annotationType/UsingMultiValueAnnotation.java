package com.java.examples.annotationType;

import com.java.examples.annotationType.annotation.MultiValueAnnotation;

@MultiValueAnnotation(id = 2, name = "Hello", roles = {"admin", "users"})
public class UsingMultiValueAnnotation {

    @MultiValueAnnotation(id = 10)
    public void testMethod() {
    }
}
