package com.folaukaveinga.springboot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.folaukaveinga.springboot.domain.User;
import com.folaukaveinga.springboot.service.UserService;


@RestController
@RequestMapping("api/users")
public class UserRestController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value={"/", ""}, method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers(){
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/{uid}", "/{uid}/"}, method=RequestMethod.GET)
	public ResponseEntity<User> getUserByUid(@PathVariable("uid") String uid){
		return new ResponseEntity<>(userService.getUserByUid(uid), HttpStatus.OK);
	}
}
