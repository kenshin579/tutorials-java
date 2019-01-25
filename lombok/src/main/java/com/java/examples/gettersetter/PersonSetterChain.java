package com.java.examples.gettersetter;

import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
public class PersonSetterChain {
    String name;

    int age;
}
