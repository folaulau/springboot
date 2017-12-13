package com.kaveinga.facebook.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping(value={"","/"})
	public String home(Model model) {
		System.out.println("home page");
		return "index";
	}
	
	@GetMapping(value={"/secure","/secure/"})
	public String secure(Model model) {
		System.out.println("secure page");
		return "secure";
	}
}
