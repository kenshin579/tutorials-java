package kr.pe.advenoh.controller;

import kr.pe.advenoh.model.Comment;
import kr.pe.advenoh.repository.CommentRepository;
import kr.pe.advenoh.service.BlogService;
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
	private BlogService blogService;

	@Autowired
	private CommentRepository commentRepository;

	@GetMapping
	public Iterable<Comment> getList() {
		return commentRepository.findAll();
	}

	@PostMapping("/post/{postId}")
	public Comment addComment(
			@PathVariable Long postId,
			Comment comment) {
		return blogService.createComment(postId, comment);
	}

	@PutMapping("/{commentId}")
	public Comment modifyComment(
			@PathVariable Long commentId,
			Comment comment) {
		return blogService.modifyComment(commentId, comment);
	}

	@Transactional
	@DeleteMapping("/{commentId}")
	public String deleteComment(@PathVariable Long commentId) {
		blogService.deleteComment(commentId);
		return "SUCCESS";
	}
}
