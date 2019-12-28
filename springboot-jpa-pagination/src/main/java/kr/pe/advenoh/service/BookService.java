package kr.pe.advenoh.service;

import kr.pe.advenoh.model.Book;
import kr.pe.advenoh.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;

	@Transactional
	public Book createBook(Book book) {
		return bookRepository.save(book);
	}

	public Page<Book> findBooksByPageRequest(Pageable pageable) {
		return bookRepository.findAll(pageable);
	}

	public Iterable<Book> findAll() {

		return null;
	}
}
