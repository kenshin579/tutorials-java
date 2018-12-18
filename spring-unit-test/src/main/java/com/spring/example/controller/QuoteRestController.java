package com.spring.example.controller;

import com.spring.example.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class QuoteRestController {

    @Autowired
    private QuoteService quoteService;

    @GetMapping("/quote")
    public ResponseEntity getQuote() {
        return new ResponseEntity(quoteService.getQuote(), HttpStatus.OK);
    }
}