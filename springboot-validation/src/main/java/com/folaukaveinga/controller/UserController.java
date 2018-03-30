package com.folaukaveinga.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.folaukaveinga.model.User;
import com.folaukaveinga.validator.UserValidator;

@Controller
public class UserController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserValidator userValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}

	@GetMapping("/user")
	public String showUserForm(Model model) {
		log.info("show user form");
		model.addAttribute("user", new User("Laulau"));
		return "user_form";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String createUser(@Valid User user, BindingResult bindingResult, Model model) {
		log.info("creating user...");
		if (bindingResult.hasErrors()) {
			log.error("user is invalid");
			bindingResult.getFieldErrors().forEach((error)->{
				log.info("Error field: {}",error.getField());
				log.info("Error code: {}",error.getCode());
				log.info("Error default msg: {}",error.getDefaultMessage());
				log.info("Error object name: {}",error.getObjectName());
				log.info("Error rejected value: {}",error.getRejectedValue());
				System.out.println("\n");
			});
			
			bindingResult.getAllErrors().forEach(error -> {
				log.info("Error : {}",error.toString());
			});
			return "user_form";
		}
		log.info("user is valid");
		model.addAttribute("user", user);
		return "redirect:/user/info";
	}

	@GetMapping("/user/info")
	public String showUserInfo(Model model) {
		log.info("showing user info");
		return "user_info";
	}
}
