package com.jenkov.tutorials.classAnnotation;

import java.lang.annotation.Annotation;

/**
 * http://tutorials.jenkov.com/java-reflection/annotations.html
 */
public class ClassAnnotationExecutor {
    public static void main(String[] args) {
        Class aClass = TheClass.class;
        Annotation[] annotations = aClass.getAnnotations();

        for (Annotation annotation : annotations) {
            if (annotation instanceof MyAnnotation) {
                MyAnnotation myAnnotation = (MyAnnotation) annotation;
                System.out.println("name: " + myAnnotation.name());
                System.out.println("value: " + myAnnotation.value());
            }
        }

        //access a specific class annotation
        Annotation annotation = aClass.getAnnotation(MyAnnotation.class);

        if (annotation instanceof MyAnnotation) {
            MyAnnotation myAnnotation = (MyAnnotation) annotation;
            System.out.println("name: " + myAnnotation.name());
            System.out.println("value: " + myAnnotation.value());
        }
    }
}
