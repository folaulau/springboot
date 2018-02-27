package com.folaukaveinga.redis.repository;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.folaukaveinga.redis.model.User;

@Repository
public class UserRepository {
	private static final String KEY = "User";
	private RedisTemplate<String, Object> redisTemplate;
	private HashOperations hashOperations;

	@Autowired
	public UserRepository(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@PostConstruct
	public void init() {
		this.hashOperations = this.redisTemplate.opsForHash();
	}

	public User add(User user) {
		// user id is hashkey 
		// user is value
		this.hashOperations.put(KEY, user.getId(), user);
		return user;
	}

	public User findUser(long id) {
		return (User) hashOperations.get(KEY, id);
	}

	public Map<Object, Object> findAllUsers() {
		return hashOperations.entries(KEY);
	}

	public long delete(final long id) {
		return hashOperations.delete(KEY, id);
	}
}
