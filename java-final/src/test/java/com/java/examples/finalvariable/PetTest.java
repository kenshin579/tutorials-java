package com.java.examples.finalvariable;

import org.junit.Test;

public class PetTest {
    @Test
    public void test_final_primitive_variables() {
        final int x = 1;
//        x = 3; //한번 assign되면 변경할 수 없음.
    }

    @Test
    public void test_final_reference_variables() {
        final Pet pet = new Pet();
//        pet = new Pet(); //다른 객체로 변경할수 없음

        pet.setWeight(3); //객체 필드는 변경할 수 있음
    }
}