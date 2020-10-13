package com.advenoh;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private String name;
    private int age;

    @Builder
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
