package com.kaveinga.user;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserListener {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@PrePersist
	public void userPrePersist(User ob) {
		log.info("userPrePersist(..)");
		log.info(ob.toJson());
		
	}
	@PostPersist
	public void userPostPersist(User ob) {
		log.info("userPostPersist(..)");
		log.info(ob.toJson());
	}
	@PostLoad
	public void userPostLoad(User ob) {
		log.info("userPostLoad(..)");
		log.info(ob.toJson());
	}	
	@PreUpdate
	public void userPreUpdate(User ob) {
		log.info("userPreUpdate(..)");
		log.info(ob.toJson());
	}
	@PostUpdate
	public void userPostUpdate(User ob) {
		log.info("userPostUpdate(..)");
		log.info(ob.toJson());
	}
	@PreRemove
	public void userPreRemove(User ob) {
		log.info("userPreRemove(..)");
		log.info(ob.toJson());
	}
	@PostRemove
	public void userPostRemove(User ob) {
		log.info("userPostRemove(..)");
		log.info(ob.toJson());
	}
}
