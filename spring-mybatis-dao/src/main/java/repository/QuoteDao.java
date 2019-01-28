package repository;

import model.Quote;

import java.util.List;

public interface QuoteDao {
	List<Quote> getQuoteList(Quote quote);

	void insertQuote(Quote quote);

	Quote selectQuote(String quoteNo);

	void updateQuote(Quote quote);

	void deleteQuote(String quoteNo);
}
