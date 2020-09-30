package com.kaveinga.user;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> get(@PathVariable("id") Long id){
		log.info("getUser({})", id);
		return new ResponseEntity<>(userService.get(id), HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAll(){
		log.info("getAll()");
		return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> save(@RequestBody User user){
		log.info("save(..)");
		return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
	}
	
	@PutMapping("/users")
	public ResponseEntity<User> update(@RequestBody User user){
		log.info("update(..)");
		return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
	}
	
	@DeleteMapping("/users")
	public ResponseEntity<Boolean> delete(@RequestBody User user){
		log.info("delete(..)");
		return new ResponseEntity<>(userService.delete(user.getId()), HttpStatus.OK);
	} 
}
