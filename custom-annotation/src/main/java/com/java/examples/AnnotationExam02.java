package com.java.examples;

public class AnnotationExam02 {
    @InsertStringData(data = "MHLab")
    private String myData;

    @InsertStringData
    private String defaultData;


    public AnnotationExam02() {
    }

    public AnnotationExam02(String myData, String defaultData) {
//        myData = "No data";
//        defaultData = "No data";
        this.myData = myData;
        this.defaultData = defaultData;
    }

    public String getMyData() {
        return myData;
    }

    public String getDefaultData() {
        return defaultData;
    }
}
