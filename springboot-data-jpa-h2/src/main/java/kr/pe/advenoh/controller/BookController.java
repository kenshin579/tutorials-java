package kr.pe.advenoh.controller;

import kr.pe.advenoh.model.Book;
import kr.pe.advenoh.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@Slf4j
@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/list")
    public Iterable<Book> getList() {
        return bookRepository.findAll();
    }

    @Transactional
    @PostMapping
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    @PutMapping("/book/{id}")
    public Book modifyBook(Book param) {
        return bookRepository.save(param);
    }

    @Transactional
    @DeleteMapping("/book/{id}")
    public String deleteBook(@PathVariable Long id) {
        Book book = new Book();
        book.setId(id);
        bookRepository.delete(book);
        return "SUCCESS";
    }
}
