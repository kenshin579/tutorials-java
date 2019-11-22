package kr.pe.advenoh.repository;

import kr.pe.advenoh.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	public Book findByTitle(String title);
	public Book findByAuthor(String author);
}