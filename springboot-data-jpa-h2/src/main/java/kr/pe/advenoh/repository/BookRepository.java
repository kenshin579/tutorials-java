package kr.pe.advenoh.repository;

import kr.pe.advenoh.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}