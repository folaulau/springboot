package com.kaveinga.user;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaveinga.utils.ObjectUtils;
import com.kaveinga.utils.RandomGeneratorUtils;

@Service
public class UserServiceImp implements UserService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@PostConstruct
	public void setup() {
		log.debug("setUp()");
		User user = new User();
		user.setFirstName("test first name");
		user.setEmail("folau+"+RandomGeneratorUtils.getUserUuid()+"@gmail.com");
		user.setUuid(RandomGeneratorUtils.getUserUuid());
		user.setLastName("test last name");
		user.setPhoneNumber("310999123123");
		user.setPassword("Test1234!");
		user.setName(user.getFirstName()+" "+user.getLastName());
		
		user = userRepository.saveAndFlush(user);
		
		log.info("user={}",ObjectUtils.toJson(user));
	}
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User create(User user) {
		// TODO Auto-generated method stub
		return userRepository.saveAndFlush(user);
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return userRepository.saveAndFlush(user);
	}

	@Override
	public User getById(long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		userRepository.delete(user);;
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}

}
