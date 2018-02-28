package com.folaukaveinga.api.security;

import java.util.HashMap;
import java.util.Map;

import com.folaukaveinga.api.model.User;

public class CacheManager {

	public static final Map<String, User> repository = new HashMap<>();
	
	public static void saveUserByToken(String token, User user) {
		repository.put(token, user);
	}
	
	public static User getUserByToken(String token) {
		return repository.get(token);
	}
}
