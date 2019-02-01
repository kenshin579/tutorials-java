package com.logicbig.example;

import org.fluttercode.datafactory.impl.DataFactory;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUserService implements UserService {
	private Map<Long, User> userMap = new HashMap<>();

	@PostConstruct
	private void postConstruct() {
		List<String> roles = Arrays.asList("admin", "real-only", "guest");
		DataFactory dataFactory = new DataFactory();
		for (int i = 1; i <= 10; i++) {
			User user = new User();
			user.setId(i);
			user.setName(dataFactory.getName());
			user.setRole(dataFactory.getItem(roles));
			userMap.put((long) i, user);
		}
	}

	public User getUserById(long id) {
		return userMap.get(id);
	}
}