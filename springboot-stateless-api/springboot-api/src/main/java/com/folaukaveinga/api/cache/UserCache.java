package com.folaukaveinga.api.cache;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.folaukaveinga.api.model.User;
@Service
public class UserCache {
	
	private static final String KEY = "User";
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	private HashOperations<String, Object, Object> hashOperations;

	@Autowired
	public UserCache(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@PostConstruct
	public void init() {
		ValueOperations<String, Object> ops = this.redisTemplate.opsForValue();
		this.hashOperations = this.redisTemplate.opsForHash();
	}

	public void add(String token, User user) {
		this.hashOperations.put(KEY, token, user);
	}

	public User findUser(String token) {
		return (User) hashOperations.get(KEY, token);
	}

	public Map<Object, Object> findAllUsers() {
		return hashOperations.entries(KEY);
	}

	public long delete(String token) {
		return hashOperations.delete(KEY, token);
	}
}
