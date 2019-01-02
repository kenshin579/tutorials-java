package com.spring.example.controller;

import com.spring.example.model.Quote;
import com.spring.example.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuoteRestController {

	@Autowired
	private QuoteService quoteService;

	@GetMapping("/quotes")
	public List getAllQuotes() {
		return quoteService.getAllQuotes();
	}

	@GetMapping("/quote/{id}")
	public ResponseEntity<?> getQuoteById(@PathVariable("id") Long id) {

		Quote Quote = quoteService.getQuoteById(id);
		if (Quote == null) {
			return new ResponseEntity<>("No Quote found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(Quote, HttpStatus.OK);
	}

	@PostMapping(value = "/quote")
	public ResponseEntity<Quote> createQuote(@RequestBody Quote Quote) {
		quoteService.createQuote(Quote);
		return new ResponseEntity<>(Quote, HttpStatus.OK);
	}

	@DeleteMapping("/quote/{id}")
	public ResponseEntity<java.io.Serializable> deleteQuoteById(@PathVariable Long id) {
		if (null == quoteService.deleteQuoteById(id)) {
			return new ResponseEntity<>("No Quote found for ID " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(id, HttpStatus.OK);

	}

	@PutMapping("/quote/{id}")
	public ResponseEntity<?> updateQuoteById(@PathVariable Long id, @RequestBody Quote Quote) {
		Quote = quoteService.updateQuoteById(id, Quote);
		if (null == Quote) {
			return new ResponseEntity<>("No Quote found for ID " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(Quote, HttpStatus.OK);
	}
}