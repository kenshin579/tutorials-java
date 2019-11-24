package kr.pe.advenoh.controller;

import kr.pe.advenoh.model.Post;
import kr.pe.advenoh.repository.PostRepository;
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

	@GetMapping
	public Iterable<Post> getList() {
		return postRepository.findAll();
	}

	@Transactional
	@PostMapping
	public Post addPost(Post post) {
		return postRepository.save(post);
	}

	@Transactional
	@PutMapping("/{id}")
	public Post modifyPost(Post post) {
		return postRepository.save(post);
	}

	@Transactional
	@DeleteMapping("/{id}")
	public String deletePost(@PathVariable Long id) {
		Post Post = new Post();
		Post.setId(id);
		postRepository.delete(Post);
		return "SUCCESS";
	}
}
