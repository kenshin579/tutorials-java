package com.java.examples.reflection;

public class ReflectionTest2 {
    public static void main(String[] args) {
        //3.Create object from Class instance

        // instance of "Class"
        Class<?> c = null;
        try {
            c = Class.forName("com.java.examples.reflection.Foo");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //create instance of "Foo"
        Foo f = null;

        try {
            f = (Foo) c.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        f.print();
    }
}

