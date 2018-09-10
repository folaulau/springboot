package com.kaveinga.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kaveinga.user.User;

@Service
public class UserAuditService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	public void send(User user) {
		log.info("send()");
		log.info(user.toJson());
	}
}
