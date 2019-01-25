package com.java.examples.annotationType;

import com.java.examples.annotationType.annotation.MakerAnnotation;

@MakerAnnotation
public class AnnotationPlacement {

    @MakerAnnotation
    String field;

    @MakerAnnotation
    public void method1(@MakerAnnotation String str) {
        @MakerAnnotation
        String test;
    }
}