package com.java.examples.equalsandhashcode;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class PersonExclude {
    String name;
    @EqualsAndHashCode.Exclude
    int age;
}
