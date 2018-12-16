package com.spring.examples.service;

import com.spring.examples.model.Quote;

import java.util.List;

public interface QuoteService {
    List<Quote> findQuotesByAuthor(String author);

    void deleteQuote(Quote quote);

    void addQuote(Quote quote);
}
