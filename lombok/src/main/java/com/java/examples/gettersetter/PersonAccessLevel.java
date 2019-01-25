package com.java.examples.gettersetter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class PersonAccessLevel {
    @Getter
    @Setter
    String name;

    @Setter(AccessLevel.PROTECTED)
    int age;
}
