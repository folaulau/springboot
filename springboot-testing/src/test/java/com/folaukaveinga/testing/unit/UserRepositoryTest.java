package com.folaukaveinga.testing.unit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.folaukaveinga.testing.user.User;
import com.folaukaveinga.testing.user.UserRepository;
import com.folaukaveinga.testing.utility.ConstantUtils;
import com.folaukaveinga.testing.utility.ObjectUtils;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;

	@Before
	public void setup() {
		
		for(int i=0;i<5;i++) {
			User user = ConstantUtils.getMember();
			user = userRepository.saveAndFlush(user);
//			
//			log.info("user={}",ObjectUtils.toJson(user));
		}
	}
	
	@Test
	public void testGet() throws Exception {
		User user = userRepository.getOne(new Long(1));
		
		log.info("user={}",ObjectUtils.toJson(user));
	}

}