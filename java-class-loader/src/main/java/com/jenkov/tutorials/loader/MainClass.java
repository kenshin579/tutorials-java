package com.jenkov.tutorials.loader;

/**
 * Dynamic Class Loading
 */
public class MainClass {

    public static void main(String[] args) {

        ClassLoader classLoader = MainClass.class.getClassLoader();

        try {
            Class aClass = classLoader.loadClass("com.jenkov.tutorials.loader.MyClass");
            System.out.println("aClass.getName() = " + aClass.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}