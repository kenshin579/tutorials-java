package com.jenkov.tutorials.fieldAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * http://tutorials.jenkov.com/java-reflection/annotations.html
 */
public class FieldAnnotationExecutor {
    public static void main(String[] args) throws NoSuchFieldException {
        Field field = TheClass.class.getField("myField");
        Annotation[] annotations = field.getDeclaredAnnotations();

        for (Annotation annotation : annotations) {
            if (annotation instanceof MyAnnotation) {
                MyAnnotation myAnnotation = (MyAnnotation) annotation;
                System.out.println("name: " + myAnnotation.name());
                System.out.println("value: " + myAnnotation.value());
            }
        }

        //
        Annotation annotation = field.getAnnotation(MyAnnotation.class);

        if (annotation instanceof MyAnnotation) {
            MyAnnotation myAnnotation = (MyAnnotation) annotation;
            System.out.println("name: " + myAnnotation.name());
            System.out.println("value: " + myAnnotation.value());
        }
    }
}
