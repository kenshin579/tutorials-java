package com.java.examples;

import java.util.Optional;

public class PersonOpt {
    private String lastName;
    private String firstName;

    private Optional<Address> address = Optional.empty();

    public PersonOpt(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Optional<String> getLastName() {
        return Optional.ofNullable(lastName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Optional<String> getFirstName() {
        return Optional.ofNullable(firstName);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
