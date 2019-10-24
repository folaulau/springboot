package com.folaukaveinga.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "users", produces = "Rest API for user operations", tags = "User Controller")
@RestController
public class UserController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "Get By Id")
	@GetMapping("/users/{id}")
	public ResponseEntity<User> get(@PathVariable("id") Long id){
		log.info("getUser({})", id);
		return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Update")
	@PutMapping("/users")
	public ResponseEntity<User> update(@RequestBody User user){
		log.info("update(..)");
		return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Delete")
	@DeleteMapping("/users")
	public ResponseEntity<Boolean> delete(@RequestBody User user){
		log.info("delete(..)");
		return new ResponseEntity<>(userService.delete(user.getId()), HttpStatus.OK);
	} 
	
	
}
