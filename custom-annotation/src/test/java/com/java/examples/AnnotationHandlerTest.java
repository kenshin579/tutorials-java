package com.java.examples;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AnnotationHandlerTest {

    @Test
    public void name() {
        AnnotationHandler handler = new AnnotationHandler();
        AnnotationExam01 exam01 = handler.getInstance(AnnotationExam01.class, InsertIntData.class)
                .map(o -> (AnnotationExam01) o)
                .orElse(new AnnotationExam01());

        AnnotationExam02 exam02 = handler.getInstance(AnnotationExam02.class, InsertStringData.class)
                .map(o -> (AnnotationExam02) o)
                .orElse(new AnnotationExam02());

        assertEquals(30, exam01.getMyAge());
        assertEquals(0, exam01.getDefaultAge());

        assertEquals("MHLab", exam02.getMyData());
        assertEquals(null, exam02.getDefaultData());
    }
}