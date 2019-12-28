package kr.pe.advenoh;

import kr.pe.advenoh.model.Book;
import kr.pe.advenoh.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JpaPaginationApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaPaginationApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDatabase(BookRepository bookRepository) {
        return (String[] args) -> {
            int maxBook = 33;
            for (int i = 0; i < maxBook; i++) {
                bookRepository.save(Book.builder()
                        .title("title" + i)
                        .author("author" + i)
                        .build());
            }
        };
    }
}
