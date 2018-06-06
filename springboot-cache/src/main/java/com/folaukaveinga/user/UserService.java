package com.folaukaveinga.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Cacheable("users")
	public User getById(Long id) {
		log.info("getById({})", id);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			log.error("InterruptedException, msg: {}",e.getLocalizedMessage());
		}
		User user = new User(id);
		user.setName("Folau");
		user.setEmail("folau@gmail.com");
		log.info(user.toString());
		return user;
	}
	
	public User save(User user) {
		return user;
	}
	
	@CachePut("users")
	public User update(User user) {
		return user;
	}
	
	@CacheEvict("users")
	public Boolean delete(Long id) {
		return true;
	}
}
