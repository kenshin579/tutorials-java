package com.java.examples.reflection;

import java.lang.reflect.Array;

public class ReflectionTest4 {
    public static void main(String[] args) {
        int[] intArray = {1, 2, 3, 4, 5};
        int[] newIntArray = (int[]) changeArraySize(intArray, 10);
        print(newIntArray);

        String[] atr = {"a", "b", "c", "d", "e"};
        String[] str1 = (String[]) changeArraySize(atr, 10);
        print(str1);
    }

    // change array size
    public static Object changeArraySize(Object obj, int len) {
        Class<?> arr = obj.getClass().getComponentType();
        Object newArray = Array.newInstance(arr, len);

        //do array copy
        int co = Array.getLength(obj);
        System.arraycopy(obj, 0, newArray, 0, co);
        return newArray;
    }

    // print
    public static void print(Object obj) {
        Class<?> c = obj.getClass();
        if (!c.isArray()) {
            return;
        }

        System.out.println("\nArray length: " + Array.getLength(obj));

        for (int i = 0; i < Array.getLength(obj); i++) {
            System.out.print(Array.get(obj, i) + " ");
        }
    }
}