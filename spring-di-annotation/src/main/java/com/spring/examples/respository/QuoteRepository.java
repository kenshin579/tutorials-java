package com.spring.examples.respository;

import com.spring.examples.model.Quote;
import com.spring.examples.service.QuoteService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuoteRepository implements QuoteService {

    private List<Quote> storage = new ArrayList();

    @Override
    public List<Quote> findQuotesByAuthor(String author) {
        return storage.stream()
                .filter(quote -> quote.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteQuote(Quote quote) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void addQuote(Quote quote) {
        storage.add(quote);
    }
}
