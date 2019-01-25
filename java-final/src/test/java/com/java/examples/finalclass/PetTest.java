package com.java.examples.finalclass;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PetTest {
    @Test
    public void test_클래스가_final_선언되어도_객체의_필드값은_변경가능하다() {
        String petName = "YoYo";
        Pet pet = new Pet();
        pet.setName(petName);
        assertEquals(petName, pet.getName());
    }
}
