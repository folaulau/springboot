package com.folaukaveinga.springboot.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.folaukaveinga.springboot.domain.Address;
import com.folaukaveinga.springboot.domain.User;
import com.folaukaveinga.springboot.utility.RandomUtil;

@Service
public class UserService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SessionFactory sessionFactory;

	public User save(User user){
		user.setId(RandomUtil.getRandomNumber());
		log.info("user saved");
		log.info(user.toString());
		
		final Session session = this.sessionFactory.openSession();
		final Transaction trans = session.beginTransaction();
		final int id = (Integer) session.save(user);
		trans.commit();
		session.close();
		user.setId(id);
		return user;
	}
	
	public User get(int id){
		log.info("get user by id: {}",id);
		final Session session = this.sessionFactory.openSession();
		User user = session.get(User.class, id);
		session.close();
		
		return user;
	}

	public List<User> getAll() {
		final Session session = this.sessionFactory.openSession();
		final Transaction trans = session.beginTransaction();
		final Query query = session.createQuery("from User");
		List<User> users = query.list();
		trans.commit();
		session.close();
		return users;
	}
}
