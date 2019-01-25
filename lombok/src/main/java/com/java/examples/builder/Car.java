package com.java.examples.builder;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class Car {
    private int wheels;
    private String color;

    public static void main(String[] args) {
        System.out.println(Car.builder().color("red").wheels(2).build());

    }
}
