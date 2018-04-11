package com.folaukaveinga.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	/**
	 * Save User
	 * @param user
	 * @return saved User
	 */
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user){
		log.info("Saving User: {}", user.toString());
		return new ResponseEntity<User>(this.userService.save(user), HttpStatus.CREATED);
	}
}
