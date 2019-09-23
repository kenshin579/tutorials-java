package com.java.circular.solution3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

@Slf4j
public class CustomerTest {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void infinite_recursion_해결책_JsonIgnore() throws JsonProcessingException {
        Order order = new Order();
        order.setOrderId(1);
        order.setItemIds(List.of(10, 30));

        Customer customer = new Customer();
        customer.setId(2);
        customer.setName("Frank");
        customer.setOrder(order);
        order.setCustomer(customer);

        log.info("customer(toString) : {}", customer);
        log.info("customer(serialized json) : {}", objectMapper.writeValueAsString(customer));
        log.info("order(serialized json) : {}", objectMapper.writeValueAsString(order));
    }
}