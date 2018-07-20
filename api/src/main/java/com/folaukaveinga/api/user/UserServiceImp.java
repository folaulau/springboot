package com.folaukaveinga.api.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImp implements UserService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private List<User> users = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		for(int i=0;i<10;i++) {
			users.add(new User((i+1), UUID.randomUUID(), RandomStringUtils.randomAlphabetic(15), RandomStringUtils.randomAlphabetic(15)+"@gmail.com", RandomStringUtils.randomAlphabetic(5)));
		}
	}
	
	@Override
	public User create(User user) {
		user.setId(users.size()+1);
		user.setUuid(UUID.randomUUID());
		users.add(user);
		return user;
	}

	@Override
	public User update(User user) {
		Iterator<User> it = users.iterator();
		while(it.hasNext()) {
			User u = it.next();
			if(user.getId()==u.getId()) {
				u.setEmail(user.getEmail());
				u.setName(user.getName());
				u.setType(user.getType());
				u.setUuid(user.getUuid());
				break;
			}
		}
		return user;
	}

	@Override
	public User getById(Integer id) {
		Iterator<User> it = users.iterator();
		while(it.hasNext()) {
			User u = it.next();
			if(id==u.getId()) {
				return u;
			}
		}
		return null;
	}

	@Override
	public User getByUuid(UUID uuid) {
		Iterator<User> it = users.iterator();
		while(it.hasNext()) {
			User u = it.next();
			if(uuid==u.getUuid()) {
				return u;
			}
		}
		return null;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return users;
	}

}
