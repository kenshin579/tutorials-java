package kr.pe.advenoh.repository;

import kr.pe.advenoh.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private BookRepository bookRepository;

	@Test
	public void name() {
		Book book = new Book();
		book.setAuthor("Frank");
		book.setPrice(100);
		entityManager.persist(book);
		entityManager.flush();

		Book result = bookRepository.findByAuthor("Frank");
		log.info("result: {}", result);

	}
}