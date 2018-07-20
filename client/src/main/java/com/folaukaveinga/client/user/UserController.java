package com.folaukaveinga.client.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "users", produces = "Rest API for User Client operations", tags = "User Client Controller")
@RestController
@RequestMapping("/users")
public class UserController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "Create User")
	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user){
		log.info("create(..)");
		log.info(user.toJson());
		return new ResponseEntity<>(userService.create(user), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Update User")
	@PutMapping
	public ResponseEntity<User> update(@RequestBody User user){
		log.info("update(..)");
		log.info(user.toJson());
		return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get All Users")
	@GetMapping
	public ResponseEntity<List<User>> getAll(){
		log.info("getAll(..)");
		return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Create User By Id")
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable("id") Integer id){
		log.info("getById({})", id);
		return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
	}
}
