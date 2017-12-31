package com.folaukaveinga.springboot.rest;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
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
	
	
	@RequestMapping(value={"/department/{department}", "/department/{department}/"}, method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsersByDepartment(@PathVariable("department") String department){
		return new ResponseEntity<>(userService.getAllUsersByDepartment(department), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/{uid}", "/{uid}/"}, method=RequestMethod.GET)
	public ResponseEntity<User> getUserByUid(@PathVariable("uid") String uid){
		return new ResponseEntity<>(userService.getUserByUid(uid), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/create", "/create/"}, method=RequestMethod.GET)
	public ResponseEntity<Boolean> save(){
		User user = new User();
		user.setEmail(RandomStringUtils.randomAlphanumeric(15)+"@gmail.com");
		user.setFirstName(RandomStringUtils.randomAlphanumeric(10));
		user.setLastName(RandomStringUtils.randomAlphanumeric(10));
		user.setDisplayName(RandomStringUtils.randomAlphanumeric(10));
		user.setPassword("test12");
		user.setUid(RandomUtils.nextInt(4000, 4999)+"");
		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/delete/{uid}", "/delete/{uid}/"}, method=RequestMethod.GET)
	public ResponseEntity<Boolean> deleteByUid(@PathVariable("uid") String uid){
		return new ResponseEntity<>(userService.deleteUser(uid), HttpStatus.OK);
	}
}
