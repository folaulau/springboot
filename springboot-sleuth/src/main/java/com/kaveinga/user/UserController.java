package com.kaveinga.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Api(value = "user", tags = "User Controller", description = "User Controller")
@Slf4j
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getById(@RequestHeader(name = "token") String token, @PathVariable long id) {
		User user = userService.getById(id);
		log.debug(user.toString());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
