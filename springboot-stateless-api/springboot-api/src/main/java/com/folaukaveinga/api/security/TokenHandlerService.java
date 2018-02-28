package com.folaukaveinga.api.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.folaukaveinga.api.model.User;

@Service
public class TokenHandlerService {
	
	public User parseUserFromToken(String token) throws Exception {
		User user = CacheManager.getUserByToken(token);
		
		if(user==null) {
			throw new RuntimeException("user not found");
		}else {
			return user;
		}
	}
}
