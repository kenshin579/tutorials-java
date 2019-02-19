package com.concretepage.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import com.concretepage.User;

public interface IUserService {
	@Secured("authenticated")
	public void methodOne();
	
	@PreAuthorize("hasRole('ADMIN')")
	public void methodTwo(String msg);
	
	@PreAuthorize ("#user.userName == authentication.name")
	public void methodThree(User user);

	@PostAuthorize ("returnObject.userName == authentication.name")
	public User methodFour();
}
