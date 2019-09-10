package com.lovemesomecoding.user;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeansException;

@Service
public class UserService implements InitializingBean {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	public User getById(long id) {
		User user = new User();
		user.setId(id);
		return user;
	}

	@PostConstruct
	public void init() {
		log.info("init()");
	}

	@PreDestroy
	public void destroy() {
		log.info("destroy()");
	}

	//this way that is discouraged due to it coupling of code to Spring.
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("afterPropertiesSet()");
	}
}
