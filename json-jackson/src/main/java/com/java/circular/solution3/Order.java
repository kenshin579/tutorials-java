package com.java.circular.solution3;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString(exclude = "customer")
public class Order {
    private int orderId;
    private List<Integer> itemIds;
    @JsonIgnore
    private Customer customer;
}