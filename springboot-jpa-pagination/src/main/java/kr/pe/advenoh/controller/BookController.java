package kr.pe.advenoh.controller;

import kr.pe.advenoh.model.Book;
import kr.pe.advenoh.repository.BookRepository;
import kr.pe.advenoh.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/book")
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookService bookService;

	@GetMapping
	public Iterable<Book> getList() {
		return bookRepository.findAll();
	}

	@GetMapping("/paging")
	public Page<Book> findBooksByPageRequest(final Pageable pageable) {
		return bookService.findBooksByPageRequest(pageable);
	}

	@PostMapping
	public Book addBook(Book book) {
		return bookService.createBook(book);
	}
}
