package com.kaveinga.authentication;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserRestController {
	
    @RequestMapping("/principal")
    public Principal user(Principal principal) {
        System.out.println(principal);
        return principal;
    }
}
