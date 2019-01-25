package com.java.examples.reflection;

import java.lang.reflect.Method;

public class ReflectionTest1 {
    public static void main(String[] args) {
        Foo f = new Foo();
        System.out.println("1. Get class name from object ==> " + f.getClass().getName());
        System.out.println();

        //메서드 이름으로 호출함
        System.out.println("2.Invoke method on unknown object");
        Method method;
        try {
            method = f.getClass().getMethod("print", new Class<?>[0]);
            method.invoke(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();

        System.out.println("Create object from Class Instance");

    }
}

