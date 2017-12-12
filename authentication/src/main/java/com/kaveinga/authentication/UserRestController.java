package com.kaveinga.authentication;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserRestController {

	@GetMapping("/principal")
	public Principal user(HttpServletRequest request) {
		System.out.println("getting principal");
		Principal principal = request.getUserPrincipal();
		System.out.println("principal name: "+principal.getName());
		return principal;
	}
}
