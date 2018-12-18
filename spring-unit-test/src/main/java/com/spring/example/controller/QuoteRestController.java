package com.spring.example.controller;

import com.spring.example.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuoteRestController {

    @Autowired
    private QuoteService quoteService;

    @GetMapping("/quote")
    public ResponseEntity getQuote() {
        return new ResponseEntity(quoteService.getQuote(), HttpStatus.OK);
    }


//
//    @GetMapping("/customers")
//    public List getCustomers() {
//        return customerDAO.list();
//    }
//
//    @GetMapping("/customers/{id}")
//    public ResponseEntity getCustomer(@PathVariable("id") Long id) {
//
//        Customer customer = customerDAO.get(id);
//        if (customer == null) {
//            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity(customer, HttpStatus.OK);
//    }
//
//    @PostMapping(value = "/customers")
//    public ResponseEntity createCustomer(@RequestBody Customer customer) {
//
//        customerDAO.create(customer);
//
//        return new ResponseEntity(customer, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/customers/{id}")
//    public ResponseEntity deleteCustomer(@PathVariable Long id) {
//
//        if (null == customerDAO.delete(id)) {
//            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity(id, HttpStatus.OK);
//
//    }
//
//    @PutMapping("/customers/{id}")
//    public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
//
//        customer = customerDAO.update(id, customer);
//
//        if (null == customer) {
//            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity(customer, HttpStatus.OK);
//    }


}