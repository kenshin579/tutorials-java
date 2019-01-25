package com.java.examples.finalfield;

import org.junit.Test;

public class CatTest {
    @Test
    public void initializeBlockTest() {
        Cat.s_value = 5; //static 초기화 블록 호출됨
        System.out.println("s_value: " + Cat.s_value);

        System.out.println();
        System.out.println("Cat 객체 생성1");
        Cat cat1 = new Cat();

        System.out.println();
        System.out.println("Cat 객체 생성2");
        Cat cat2 = new Cat();
    }
}