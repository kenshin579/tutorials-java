package com.spring.examples.service;

import com.spring.examples.model.Quote;
import com.spring.examples.respository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    @Override
    public List<Quote> findQuotesByAuthor(String author) {
        return quoteRepository.findQuotesByAuthor(author);
    }

    @Override
    public void deleteQuote(Quote quote) {
        quoteRepository.deleteQuote(quote);
    }

    @Override
    public void addQuote(Quote quote) {
        quoteRepository.addQuote(quote);
    }
}
