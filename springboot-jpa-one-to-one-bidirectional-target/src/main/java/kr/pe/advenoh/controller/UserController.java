package kr.pe.advenoh.controller;

import kr.pe.advenoh.model.User;
import kr.pe.advenoh.repository.UserRepository;
import kr.pe.advenoh.service.UserService;
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
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository postRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public Iterable<User> getList() {
        return postRepository.findAll();
    }

    @PostMapping
    public User addUser(User user) {
        return userService.createUser(user);
    }

    @Transactional
    @PutMapping("/{postId}")
    public User modifyUser(
            @PathVariable Long postId,
            User post) {
        return userService.modifyUser(postId, post);
    }

    @Transactional
    @DeleteMapping("/{postId}")
    public String deleteUser(@PathVariable Long postId) {
        userService.deleteUser(postId);
        return "SUCCESS";
    }
}
