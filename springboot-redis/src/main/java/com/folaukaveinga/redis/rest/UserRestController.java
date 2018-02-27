package com.folaukaveinga.redis.rest;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.folaukaveinga.redis.model.User;
import com.folaukaveinga.redis.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value={"","/"}, method=RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Object,Object>> getAllUsers(HttpServletRequest request, HttpServletResponse response){
		log.info("get all users");
		return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
	}

	@RequestMapping(value={"/add-user","/add-user/"}, method=RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> addUser(@RequestBody User user){
		log.info("add user id, {}", user.getId());
		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/remove-user/{id}","/remove-user/{id}/"}, method=RequestMethod.DELETE ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> deleteUser(@PathVariable long id){
		log.info("remove user id: {}",id);
		return new ResponseEntity<>(userService.removeUser(id), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/{id}","/{id}/"}, method=RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> findUserById(@PathVariable long id){
		log.info("find user id: {}",id);
		return new ResponseEntity<>(userService.findUser(id), HttpStatus.OK);
	}
}
