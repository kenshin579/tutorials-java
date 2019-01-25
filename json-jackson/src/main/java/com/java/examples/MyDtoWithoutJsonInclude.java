package com.java.examples;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class MyDtoWithoutJsonInclude {
    private String stringValue;

    private int intValue;

    boolean booleanValue;
}
