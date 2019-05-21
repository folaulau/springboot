package com.lovemesomecoding.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lovemesomecoding.model.User;
import com.lovemesomecoding.utils.ObjectUtils;

@Repository
public class UserDAO {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private SessionFactory sessionFactory;

	
	public User saveAndFlush(User user) {
		log.debug("saveAndFlush(..)");
		Session session = sessionFactory.openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Object id = session.save(user);
		transaction.commit();
		session.close();
		log.debug("id={}",ObjectUtils.toJson(id));
		user.setId((long)id);
		
		return user;
	}
	
	public User update(User user) {
		log.debug("saveAndFlush(..)");
		Session session = sessionFactory.openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.update(user);
		transaction.commit();
		session.close();
		
		return this.getById(user.getId());
	}
	
	public User getById(Long memberId) {
		Session session = sessionFactory.openSession();
		return session.get(User.class, memberId);
	}
}
