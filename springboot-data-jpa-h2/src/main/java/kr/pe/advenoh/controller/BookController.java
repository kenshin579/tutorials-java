package kr.pe.advenoh.controller;

import kr.pe.advenoh.model.Book;
import kr.pe.advenoh.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Get All Book Data
     *
     * @return
     */
    @GetMapping("/book/list")
    public Iterable<Book> getList() {
        return bookRepository.findAll();
    }

    /**
     * View specific Book by id
     *
     * @param id
     * @return
     */
    @GetMapping("/book/{id}")
    public Book getDetail(@PathVariable Long id) {
//        return bookRepository.findOne(id);
        return null;
    }

    /**
     * New Book Data
     *
     * @param param
     * @return
     */
    @Transactional
    @PostMapping("/book")
    public Book addBook(Book param) {
        return bookRepository.save(param);
    }

    /**
     * Modify Book Data
     *
     * @param param
     * @return
     */
    @Transactional
    @PutMapping("/book/{id}")
    public Book modifyBook(Book param) {
        return bookRepository.save(param);
    }

    /**
     * Delete Book Data
     *
     * @param id
     * @return
     */
    @Transactional
    @DeleteMapping("/book/{id}")
    public String deleteBook(@PathVariable Long id) {
//        bookRepository.delete(id);
        return "SUCCESS";
    }
}
