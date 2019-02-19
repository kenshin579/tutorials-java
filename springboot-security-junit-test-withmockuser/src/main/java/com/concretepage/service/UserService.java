package com.concretepage.service;

import org.springframework.stereotype.Service;

import com.concretepage.User;

@Service
public class UserService implements IUserService {
	@Override
	public void methodOne() {
		System.out.println("--Method One--");
	}
	@Override
	public void methodTwo(String msg) {
		System.out.println("MSG:"+msg);
	}
	@Override
	public void methodThree(User user) {
		System.out.println("User Name:"+user.getUserName());
	}
	@Override
	public User methodFour() {
		User user = new User();
		user.setUserName("ram");
		return user;
	}
}
