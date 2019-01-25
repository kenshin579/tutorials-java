package com.java.examples.tostring;

import lombok.ToString;

@ToString
public class PersonExclude {
    @ToString.Include
    String name;

    @ToString.Exclude
    int age;
}

