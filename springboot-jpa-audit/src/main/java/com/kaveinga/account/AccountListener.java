package com.kaveinga.account;

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
public class AccountListener {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@PrePersist
	public void accountPrePersist(Account ob) {
		log.info("accountPrePersist(..)");
		log.info(ob.toJson());
		
	}
	@PostPersist
	public void accountPostPersist(Account ob) {
		log.info("accountPostPersist(..)");
		log.info(ob.toJson());
	}
	@PostLoad
	public void accountPostLoad(Account ob) {
		log.info("accountPostLoad(..)");
		log.info(ob.toJson());
	}	
	@PreUpdate
	public void accountPreUpdate(Account ob) {
		log.info("accountPreUpdate(..)");
		log.info(ob.toJson());
	}
	@PostUpdate
	public void accountPostUpdate(Account ob) {
		log.info("accountPostUpdate(..)");
		log.info(ob.toJson());
	}
	@PreRemove
	public void accountPreRemove(Account ob) {
		log.info("accountPreRemove(..)");
		log.info(ob.toJson());
	}
	@PostRemove
	public void accountPostRemove(Account ob) {
		log.info("accountPostRemove(..)");
		log.info(ob.toJson());
	}
}
