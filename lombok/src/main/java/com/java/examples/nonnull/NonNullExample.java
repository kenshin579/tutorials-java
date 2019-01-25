package com.java.examples.nonnull;


import com.java.examples.model.Person;
import lombok.NonNull;

public class NonNullExample extends Something {
    private String name;

    public NonNullExample(@NonNull Person person) {
        super("Hello");
        this.name = person.getName();
    }
}