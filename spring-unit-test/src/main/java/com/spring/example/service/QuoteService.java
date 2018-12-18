package com.spring.example.service;

import com.spring.example.model.Quote;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {
    public Quote getQuote() {
        Quote quote = new Quote("에디슨", "책을 읽는 다는 것은 많은 경우에 자신의 미래를 만드는 것과 같은 뜻이다");
        return quote;
    }
}
