package com.spring.examples;

import com.spring.examples.model.Quote;
import com.spring.examples.service.QuoteService;
import com.spring.examples.spring.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

@Slf4j
public class QuoteMain {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        QuoteService quoteService = ctx.getBean(QuoteService.class);

        quoteService.addQuote(new Quote("평생 살 것처럼 공부하고, 내일 죽을 것처럼 살아라", "간디"));
        List<Quote> quotes = quoteService.findQuotesByAuthor("간디");
        log.info("quotes: {}", quotes);
    }
}
