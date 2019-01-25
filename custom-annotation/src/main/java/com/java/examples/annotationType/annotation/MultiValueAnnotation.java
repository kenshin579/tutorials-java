package com.java.examples.annotationType.annotation;

public @interface MultiValueAnnotation {
    int id();

    String name() default "user";

    String[] roles() default {"anonymous"};
}
