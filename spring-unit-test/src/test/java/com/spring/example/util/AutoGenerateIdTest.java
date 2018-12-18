package com.spring.example.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AutoGenerateIdTest {

    @Test
    public void incrementAndGet() {
        assertEquals(1, AutoGenerateId.incrementAndGet());
        assertEquals(2, AutoGenerateId.incrementAndGet());
        assertEquals(3, AutoGenerateId.incrementAndGet());
    }
}