package com.kaveinga.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UserAuditService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Async
	public void audit(User user) {
		log.info("audit - {}",user.toJson());
	}
}
