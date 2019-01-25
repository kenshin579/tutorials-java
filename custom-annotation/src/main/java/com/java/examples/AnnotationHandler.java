package com.java.examples;

import java.lang.reflect.Field;
import java.util.Optional;

public class AnnotationHandler {
    private <T> T checkAnnotation(T targetObj, Class annotationObj) {
        Field[] fields = targetObj.getClass().getDeclaredFields();
        for (Field f : fields) {
            if (annotationObj == InsertIntData.class) {
                return checkAnnotation4InsertInt(targetObj, f);
            } else if (annotationObj == InsertStringData.class) {
                return checkAnnotation4InsertString(targetObj, f);
            }
        }
        return targetObj;
    }

    private <T> T checkAnnotation4InsertInt(T targetObj, Field field) {
        InsertIntData annotation = field.getAnnotation(InsertIntData.class);
        if (annotation != null && field.getType() == int.class) {
            field.setAccessible(true);
            try {
                field.set(targetObj, annotation.data());
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }
        return targetObj;
    }

    private <T> T checkAnnotation4InsertString(T targetObj, Field field) {
        InsertStringData annotation = field.getAnnotation(InsertStringData.class);
        if (annotation != null && field.getType() == String.class) {
            field.setAccessible(true);
            try {
                field.set(targetObj, annotation.data());
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }
        return targetObj;
    }

    public <T> Optional<T> getInstance(Class targetClass, Class annotationClass) {
        Optional optional = Optional.empty();
        Object object;
        try {
            object = targetClass.newInstance();
            object = checkAnnotation(object, annotationClass);
            optional = Optional.of(object);
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
        return optional;
    }
}
