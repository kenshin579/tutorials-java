package com.spring.examples;

import com.spring.examples.model.Quote;
import com.spring.examples.service.QuoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

@Slf4j
public class QuoteMain {

    public static void main(String[] args) {
        BeanFactory ctx = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");

        QuoteService quoteService = ctx.getBean(QuoteService.class);

        quoteService.addQuote(new Quote("평생 살 것처럼 공부하고, 내일 죽을 것처럼 살아라", "간디"));
        List<Quote> quotes = quoteService.findQuotesByAuthor("간디");
        log.info("quotes: {}", quotes);
    }
}
