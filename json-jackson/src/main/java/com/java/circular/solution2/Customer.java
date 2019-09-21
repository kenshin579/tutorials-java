package com.java.circular.solution2;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Customer {
    private int id;
    private String name;
    @JsonManagedReference //serialized될 때 포함됨
    private Order order;
}