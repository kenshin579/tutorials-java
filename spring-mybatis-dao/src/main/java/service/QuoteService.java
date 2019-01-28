package service;

import model.Quote;

import java.util.List;

public interface QuoteService {
	List<Quote> find(Quote quote);

	void add(Quote quote);

	Quote view(String quoteNo);

	void edit(Quote quote);

	void remove(String quoteNo);
}
