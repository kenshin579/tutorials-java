package com.jenkov.tutorials.methodAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class MethodAnnotationExecutor {
    public static void main(String[] args) throws NoSuchMethodException {
        Method method = TheClass.class.getMethod("doThis");
        Annotation[] annotations = method.getDeclaredAnnotations();

        for (Annotation annotation : annotations) {
            if (annotation instanceof MyAnnotation) {
                MyAnnotation myAnnotation = (MyAnnotation) annotation;
                System.out.println("name: " + myAnnotation.name());
                System.out.println("value: " + myAnnotation.value());
            }
        }

        Annotation annotation = TheClass.class.getMethod("doThat")
                .getAnnotation(MyAnnotation.class);

        if (annotation instanceof MyAnnotation) {
            MyAnnotation myAnnotation = (MyAnnotation) annotation;
            System.out.println("name: " + myAnnotation.name());
            System.out.println("value: " + myAnnotation.value());
        }
    }
}
