package com.java.examples.finalfield;

public class Cat extends Pet {
    final int i_value;
    static int s_value;

    {
        System.out.println("instance initializer block");
        i_value = 3;
        System.out.println("i_value: " + i_value);
        System.out.println("s_value: " + s_value);
    }

    static {
        System.out.println("static initializer block");
//        System.out.println("i_value: " + i_value); //static 블록에서 필드 접근 안됨
        s_value = 10;
        System.out.println("s_value: " + s_value);

    }

    public Cat() {
        System.out.println("contructor: Cat");
    }
}
