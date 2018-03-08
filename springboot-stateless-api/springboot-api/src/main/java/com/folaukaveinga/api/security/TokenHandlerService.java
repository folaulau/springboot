package com.folaukaveinga.api.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.folaukaveinga.api.cache.UserCache;
import com.folaukaveinga.api.model.User;

@Service
public class TokenHandlerService {
	@Autowired
	UserCache userCache;
	
	public User parseUserFromToken(String token) throws Exception {
		User user = userCache.findUser(token);
		
		if(user==null) {
			throw new RuntimeException("user not found");
		}else {
			return user;
		}
	}
}
