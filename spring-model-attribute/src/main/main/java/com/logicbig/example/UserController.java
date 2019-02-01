package com.logicbig.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("users")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("{userId}")
	public String handleRequestById(@ModelAttribute("user") User user, Model model) {
		model.addAttribute("msg", "user  : " + user);
		return user != null && "admin".equals(user.getRole()) ? "admin-page" : "user-page";
	}

	@ModelAttribute("user")
	public User getUser(@PathVariable("userId") long userId) {
		return userService.getUserById(userId);
	}
}