package com.java.examples.gettersetter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


public class Person {
    @Getter
    @Setter
    String name;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    int age;
}
