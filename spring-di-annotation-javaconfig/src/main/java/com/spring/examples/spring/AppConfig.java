package com.spring.examples.spring;

import com.spring.examples.respository.QuoteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.spring.examples")
public class AppConfig {
    //todo: javaconfig으로 동작을 하지 않음.
//    @Bean
//    public QuoteRepository getQuoteRepository(QuoteRepository quoteRepository) {
//        return quoteRepository;
//    }
}
