package com.java.examples.reflection;

import java.lang.reflect.Constructor;

public class ReflectionTest3 {
    public static void main(String[] args) {
        //4.Get constructor and create instance


        //create instance of "Class"
        Class<?> c = null;
        try {
            c = Class.forName("com.java.examples.reflection.Foo2");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //create instance of "Foo"
        Foo2 f1 = null;
        Foo2 f2 = null;

        //get all constructors
        Constructor<?> cons[] = c.getConstructors();

        try {
            f1 = (Foo2) cons[0].newInstance();
            f2 = (Foo2) cons[1].newInstance("abc");
        } catch (Exception e) {
            e.printStackTrace();
        }

        f1.print();
        f2.print();
    }
}

