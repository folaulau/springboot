package com.kaveinga.restdoc.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaveinga.restdoc.model.User;

@RestController
@RequestMapping("/users/")
public class UserController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("{id}")
	public User getUserById(@PathVariable Integer id) {
		log.info("get user by id: {}", id);
		return new User("Folau",45);
	}
}
