package kr.pe.advenoh.controller;

import kr.pe.advenoh.model.Post;
import kr.pe.advenoh.repository.PostRepository;
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
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private BlogService blogService;

	@GetMapping
	public Iterable<Post> getList() {
		return postRepository.findAll();
	}

	@PostMapping
	public Post addPost(Post post) {
		return blogService.createPost(post);
	}

	@Transactional
	@PutMapping("/{postId}")
	public Post modifyPost(
			@PathVariable Long postId,
			Post post) {
		return blogService.modifyPost(postId, post);
	}

	@Transactional
	@DeleteMapping("/{postId}")
	public String deletePost(@PathVariable Long postId) {
		blogService.deletePost(postId);
		return "SUCCESS";
	}
}
