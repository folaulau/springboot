package com.maven.user.rest;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maven.library.domain.User;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@GetMapping({"","/"})
	public List<User> getUsers() {
		log.info("get all users");
		return Arrays.asList(new User(1),new User(2),new User(3));
	}
}
