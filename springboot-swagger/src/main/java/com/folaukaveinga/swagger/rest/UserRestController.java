package com.folaukaveinga.swagger.rest;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.folaukaveinga.swagger.model.User;
import com.folaukaveinga.swagger.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value = "user",produces = "Rest API for user operations", tags = "User API")
@RestController
@RequestMapping("api/users")
public class UserRestController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	

	@ApiOperation(value = "Create user")
	@RequestMapping(value={"/",""}, method=RequestMethod.POST)
	public ResponseEntity<User> create(@RequestBody User user){
		log.info("create user: "+user.toString());
		return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Get user")
	@RequestMapping(value={"/{id}","/{id}/"}, method=RequestMethod.GET)
	public ResponseEntity<User> get(@PathVariable("id")int id){
		log.info("get user by id: {}", id);
		return new ResponseEntity<>(userService.get(id), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Create list of users")
	@RequestMapping(value={"","/"}, method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAll(){
		log.info("get all users");
		return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Update user")
	@RequestMapping(value={"/update","/update/"}, method=RequestMethod.POST)
	public ResponseEntity<User> update(@RequestBody User user){
		log.info("update user: "+user.toString());
		return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
	}

}
