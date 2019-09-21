package com.java.circular.solution1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class CustomerTest {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void infinite_recursion_해결책1_JsonIdentityReference() throws JsonProcessingException {
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
    }

    @Test
    public void JsonIdentityReferenceAnnotation이_있는_경우() throws JsonProcessingException {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Frank");
        assertThat(objectMapper.writeValueAsString(customer)).isEqualTo("1");
    }

    @Test
    public void JsonIdentityReferenceAnnotation이_없는_경우() throws JsonProcessingException {
        CustomerWithoutIdentityReference customer = new CustomerWithoutIdentityReference();
        customer.setId(1);
        customer.setName("Frank");
        assertThat(objectMapper.writeValueAsString(customer)).isEqualTo("{\"id\":1,\"name\":\"Frank\",\"order\":null}");
    }
}