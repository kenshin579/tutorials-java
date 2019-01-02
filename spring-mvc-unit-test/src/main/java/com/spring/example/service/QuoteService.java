package com.spring.example.service;

import com.spring.example.model.Quote;
import com.spring.example.util.AutoGenerateId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuoteService {
//    private static int generatedId = 0;

    // Dummy database. Initialize with some dummy values.
    private static List<Quote> quotes;

    {
        quotes = new ArrayList();
        quotes.add(new Quote(AutoGenerateId.incrementAndGet(), "에디슨", "책을 읽는 다는 것은 많은 경우에 자신의 미래를 만드는 것과 같은 뜻이다"));
        quotes.add(new Quote(AutoGenerateId.incrementAndGet(), "루머 고든", "독서를 배우면 다시 태어나게 된다"));
        quotes.add(new Quote(AutoGenerateId.incrementAndGet(), "빌 게이츠", "오늘의 나를 있게 한 것은 우리 마을 도서관이었고, 하버드 졸업장보다 소중한 것이 독서하는 습관이다"));
    }

    public List getAllQuotes() {
        return quotes;
    }

    public Quote getQuoteById(Long id) {
        return quotes.stream().filter(quote -> quote.getId().equals(id)).findFirst().orElse(null);
    }

    public Quote createQuote(Quote Quote) {
        Quote.setId(AutoGenerateId.incrementAndGet());
        quotes.add(Quote);
        return Quote;
    }

    public Long deleteQuoteById(Long id) {
        int size = quotes.size();
        for (Quote quote : quotes) {
            if (quote.getId().equals(id)) {
                quotes.remove(quote);
                return id;
            }
        }
        return null;
    }

    public Quote updateQuoteById(Long id, Quote Quote) {
        for (Quote c : quotes) {
            if (c.getId().equals(id)) {
                Quote.setId(c.getId());
                quotes.remove(c);
                quotes.add(Quote);
                return Quote;
            }
        }
        return null;
    }
}
