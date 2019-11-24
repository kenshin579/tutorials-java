package kr.pe.advenoh.controller;

import kr.pe.advenoh.model.Comment;
import kr.pe.advenoh.repository.CommentRepository;
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
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
	private CommentRepository commentRepository;

	@GetMapping
	public Iterable<Comment> getList() {
		return commentRepository.findAll();
	}

	@Transactional
	@PostMapping
	public Comment addComment(Comment comment) {
		return commentRepository.save(comment);
	}

	@Transactional
	@PutMapping("/{id}")
	public Comment modifyComment(Comment comment) {
		return commentRepository.save(comment);
	}

	@Transactional
	@DeleteMapping("/{id}")
	public String deleteComment(@PathVariable Long id) {
		Comment comment = new Comment();
		comment.setId(id);
		commentRepository.delete(comment);
		return "SUCCESS";
	}
}
