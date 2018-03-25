package com.folaukaveinga.springbootexceptionhandling.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.folaukaveinga.springbootexceptionhandling.domain.User;
import com.folaukaveinga.springbootexceptionhandling.service.UserService;

@RestController

public class UserRestController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@PostMapping({"/api/users","/api/users/"})
	public ResponseEntity<?> addUser(@RequestBody User user){
		log.info("Hello save user: ",user.toString());
		return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
	}
	
	@PostMapping({"/api/users/batch","/api/users/batch/"})
	public ResponseEntity<?> addUsers(@RequestBody List<User> users){
		log.info("Hello save users: ",users.toString());
		return new ResponseEntity<>(userService.saveUsers(users), HttpStatus.OK);
	}
	
	@GetMapping({"/api/users/{id}","/api/users/{id}"})
	public ResponseEntity<?> getUser(@PathVariable int id){
		log.info("Hello get user by id: ", id);
		return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
	}
}
