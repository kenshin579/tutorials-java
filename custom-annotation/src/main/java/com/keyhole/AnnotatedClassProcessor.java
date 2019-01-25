package com.keyhole;

import com.keyhole.annotation.AnnotatedClass;
import com.keyhole.annotation.DoItLikeThat;
import com.keyhole.annotation.DoItLikeThis;
import com.keyhole.annotation.DoItWithAWhiffleBallBat;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotatedClassProcessor {

    public void processClass(AnnotatedClass ac) {
        System.out.println("------Class Processing Begin---------");

        System.out.println("Class: " + ac.getClass().getName());
        if (ac.getClass().isAnnotationPresent(DoItLikeThis.class)) {
            // process the annotation, "ac" being the instance of the object we are inspecting
            DoItLikeThis anno = ac.getClass().getAnnotation(DoItLikeThis.class);
            System.out.println("Action: " + anno.action());
            System.out.println("Description: " + anno.description());
            System.out.println("DoItLikeThis:" + anno.shouldDoItLikeThis());

            System.out.println("------Field Processing---------");
            Field[] fields = ac.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(DoItLikeThat.class)) {
                    DoItLikeThat fAnno = field.getAnnotation(DoItLikeThat.class);
                    System.out.println("Field: " + field.getName());
                    System.out.println("DoItLikeThat:" + fAnno.shouldDoItLikeThat());
                    for (String role : fAnno.roles()) {
                        System.out.println("Role: " + role);
                    }
                }
            }

            System.out.println("------Method Processing---------");
            Method[] methods = ac.getClass().getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(DoItWithAWhiffleBallBat.class)) {
                    DoItWithAWhiffleBallBat mAnno = method.getAnnotation(DoItWithAWhiffleBallBat.class);
                    System.out.println("Use WhiffleBallBat? " + mAnno.shouldDoItWithAWhiffleBallBat());
                    System.out.println("Which WhiffleBallBat? " + mAnno.batType());
                }
            }

        }
        System.out.println("------Class Processing End---------");
    }
}