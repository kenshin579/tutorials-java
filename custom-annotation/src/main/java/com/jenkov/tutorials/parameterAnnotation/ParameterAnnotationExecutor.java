package com.jenkov.tutorials.parameterAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ParameterAnnotationExecutor {
    public static void main(String[] args) throws NoSuchMethodException {
        Method method = TheClass.class.getMethod("doSomethingElse", String.class);
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Class[] parameterTypes = method.getParameterTypes();

        int i = 0;
        for (Annotation[] annotations : parameterAnnotations) {
            Class parameterType = parameterTypes[i++];

            for (Annotation annotation : annotations) {
                if (annotation instanceof MyAnnotation) {
                    MyAnnotation myAnnotation = (MyAnnotation) annotation;
                    System.out.println("param: " + parameterType.getName());
                    System.out.println("name : " + myAnnotation.name());
                    System.out.println("value: " + myAnnotation.value());
                }
            }
        }
    }
}
