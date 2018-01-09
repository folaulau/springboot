package com.folaukaveinga.ldap.web;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
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

	@GetMapping(value={"/users","/users/"})
	public String displayUsers(Model model){
		log.info("display users");
		List<LdapUser> ldapUsers = userService.getAllUsers();
		log.info(ldapUsers.toString());
		model.addAttribute("users", ldapUsers);
		return "users";
	}
	
	@GetMapping(value={"/department/{id}","/department/{id}/"})
	public String displayUsersByDepartment(Model model, @PathVariable("id") String department){
		log.info("display users by department: {}", department);
		List<LdapUser> ldapUsers = userService.getUsersByDepartment(department);
		log.info(ldapUsers.toString());
		model.addAttribute("users", ldapUsers);
		return "users";
	}
	
	@GetMapping(value={"/user/{uid}","/user/{uid}"})
	public String displayUser(Model model, @PathVariable("uid") String uid){
		log.info("display user, uid: {} ", uid);
		LdapUser ldapUser = userService.getUser(uid);
		log.info("LDAP-USER: "+ldapUser.toString());
		model.addAttribute("user", ldapUser);
		return "user";
	}
	
	@GetMapping(value={"/login","/login/"})
	public String displayLogin(){
		log.info("display login page");
		return "login";
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
	
	@GetMapping(value={"/email-change","/email-change/"})
	public String displayEmailChange(){
		log.info("display email change page");
		return "email_change";
	}
	
	@PostMapping(value={"/email-change","/email-change/"})
	public String processEmailChange(Model model, @RequestParam(value="uid") String uid, @RequestParam(value="newEmail") String newEmail){
		log.info("process email change");
		boolean result = userService.changeEmail(uid, newEmail);
		if(result){
			return "redirect:/user/"+uid;
		}else{
			model.addAttribute("emailChangeStatus", "error");
			return "email_change";
		}
		
	}
	
	@GetMapping(value={"/user-create","/user-create/"})
	public String displayUserCreate(Model model){
		log.info("display user create page");
		LdapUser userLdap = new LdapUser();
		userLdap.setFirstName(RandomStringUtils.randomAlphabetic(6));
		userLdap.setLastName(RandomStringUtils.randomAlphabetic(6));
		userLdap.setEmail(RandomStringUtils.randomAlphabetic(10)+"@gmail.com");
		userLdap.setDepartment(999111999+"");
		model.addAttribute("user", userLdap);
		return "user_create";
	}
	
	@PostMapping(value={"/user-create","/user-create/"})
	public String processUserCreate(Model model, 
			@RequestParam(value="firstName") String firstName,
			@RequestParam(value="lastName") String lastName,
			@RequestParam(value="department") String department,
			@RequestParam(value="password") String password,
			@RequestParam(value="email") String email){
		
		log.info("process user create");
		LdapUser userLdap = new LdapUser(firstName,lastName,department,email,password);
		userLdap.setCn(firstName+" "+lastName);
		userLdap.setId(RandomUtils.nextInt(1000, 9999)+"");
		userLdap.setDisplayName(firstName+" "+lastName);
		
		log.info(userLdap.toString());
		boolean result = userService.create(userLdap);
		if(result){
			return "redirect:/user/"+userLdap.getId();
		}else{
			model.addAttribute("createStatus", "error");
			return "user_create";
		}
		
	}
}
