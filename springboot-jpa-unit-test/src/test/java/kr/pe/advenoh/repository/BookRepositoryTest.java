package kr.pe.advenoh.repository;

import kr.pe.advenoh.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void save_book_확인() {
        bookRepository.save(new Book("book title", "Frank", 100));
        List<Book> books = bookRepository.findAll();
        assertThat(books.size()).isEqualTo(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Frank");
    }
}