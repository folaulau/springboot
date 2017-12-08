package com.folaukaveinga.springboot.rest;

import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.folaukaveinga.springboot.domain.User;
import com.folaukaveinga.springboot.jms.Mail;
import com.folaukaveinga.springboot.jms.SenderService;
import com.folaukaveinga.springboot.service.UserService;


@RestController
@RequestMapping("api/users")
public class UserRestController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value={"/",""}, method=RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> create(@RequestBody User user, @RequestParam(value="name",required=false)String name, @RequestParam(value="age",required=false)int age){
		log.info("name: {}, age: {}", name, age);
		log.info("Body user: "+user.toString());
		return new ResponseEntity<>(userService.save(new User(name,age)), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/{id}","/{id}/"}, method=RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> get(@PathVariable("id")int id){
		log.info("get user by id: {}", id);
//		if(1==1){
//			throw new RuntimeException("test");
//		}
		
		return new ResponseEntity<>(userService.get(id), HttpStatus.OK);
	}
}
