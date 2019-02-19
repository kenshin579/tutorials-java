package com.concretepage;

import com.concretepage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/app")
public class AppController {
	@Autowired
	public IUserService userService;

	@RequestMapping("/admin")
	public @ResponseBody String getAdminInfo() {
		//only admin role can access
		userService.methodTwo("Hello");
		String msg = "Welcome to Admin.";
		return msg;
	}

	@RequestMapping("/user")
	public @ResponseBody String getUserInfo() {
		//Access denied to user role
		userService.methodTwo("Hello");
		String msg = "Welcome to User.";
		return msg;
	}
}	