package com.lovemesomecoding.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.lovemesomecoding.dto.UserMapper;
import com.lovemesomecoding.utils.ObjectUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "user-views",produces = "Rest API for user view operations", tags = "User View Controller")
@Controller
public class UserController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 
	 * @param apiKey
	 * @param user
	 * @return
	 */
	
	@ApiOperation(value = "Home Page")
	@GetMapping("/home")
	public String home(Model model){
		log.debug("home(..)");
		
		model.addAttribute("message", "Welcome to my page");
		
		log.debug("model={}",ObjectUtils.toJson(model));
		
		return "home";
	}
}
