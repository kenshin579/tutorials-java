package com.java.examples;

public class AnnotationExam01 {
    @InsertIntData(data = 30)
    private int myAge;

    @InsertIntData
    private int defaultAge;

    public void AnnotationExam01() {
        this.myAge = -1;
        this.defaultAge = -1;
    }

    public int getMyAge() {
        return myAge;
    }

    public int getDefaultAge() {
        return defaultAge;
    }
}
