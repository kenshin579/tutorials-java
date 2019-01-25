package com.keyhole;

import com.keyhole.annotation.AnnotatedOne;
import com.keyhole.annotation.AnnotatedTwo;

public class RunProcessor {

    /**
     * @param args
     */
    public static void main(String[] args) {
        AnnotatedClassProcessor processor = new AnnotatedClassProcessor();
        processor.processClass(new AnnotatedOne());
        processor.processClass(new AnnotatedTwo());
    }
}