package com.java.examples;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyDto {
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String stringValue;

    private int intValue;

    boolean booleanValue;
}
