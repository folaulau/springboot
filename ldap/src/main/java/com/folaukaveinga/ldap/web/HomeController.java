package com.folaukaveinga.ldap.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.folaukaveinga.ldap.domain.LdapUser;
import com.folaukaveinga.ldap.service.UserService;

@Controller
public class HomeController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value={"","/"})
	public String home(){
		log.info("Welcome home");
		return "index";
	}
	
	@GetMapping(value={"/login","/login/"})
	public String displayLogin(){
		log.info("display login page");
		return "login";
	}
	
	@GetMapping(value={"/user/{uid}","/user/{uid}"})
	public String displayUser(Model model, @PathVariable("uid") String uid){
		log.info("display user");
		LdapUser ldapUser = userService.getUser(uid);
		log.info("LDAP-USER: "+ldapUser.toString());
		model.addAttribute("user", ldapUser);
		return "user";
	}
	
	@PostMapping(value={"/login","/login/"})
	public String processLogin(Model model, @RequestParam(value="uid") String uid, @RequestParam(value="password") String password){
		log.info("process login");
		boolean result = userService.login(uid, password);
		if(result){
			return "redirect:/user/"+uid;
		}else{
			model.addAttribute("loginStatus", "error");
			return "login";
		}
		
		
		
		
	}
}
