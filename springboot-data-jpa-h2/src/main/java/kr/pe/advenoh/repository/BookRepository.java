package kr.pe.advenoh.repository;

import kr.pe.advenoh.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}