package com.kaveinga.user;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "users",produces = "Rest API for user operations", tags = "User Controller")
@RestController
@RequestMapping("/users")
public class UserController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	/**
	 * 
	 * @param apiKey
	 * @param user
	 * @return
	 */
	
	@ApiOperation(value = "Create User")
	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user){
		log.info("create(..)", user.toJson());
		user = userService.create(user);
		log.info("user created");
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Update User")
	@PutMapping
	public ResponseEntity<User> update(@RequestBody User user){
		log.info("update({})", user.toJson());
		user = userService.update(user);
		log.info("user updated");
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get By Id")
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable("id") Long id){
		log.info("getById({})", id);
		User user = userService.getById(id);
		log.info("user returned");
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get All Users")
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAll(){
		log.info("getAll()");
		List<User> users = userService.getAll();
		log.info("users returned");
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Delete By Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
		log.info("delete({})", id);
		
		this.userService.remove(id);
		log.info("user deleted");
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
}
