package com.folaukaveinga.testing.unit;

import java.util.Random;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.folaukaveinga.testing.user.User;
import com.folaukaveinga.testing.user.UserRepository;
import com.folaukaveinga.testing.user.UserService;

@RunWith(SpringRunner.class)
//@DataJpaTest(showSql=true)
@SpringBootTest(classes= {UserService.class})
public class UserServiceTest {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

//	@Autowired
//	private UserService userService;
	
	@Autowired
	 private UserRepository userRepository;
	
//	@Before
//	public void init() {
//		userService = new UserService(userRepository);
//	}

//	@Test
//	public void testSave() throws Exception {
//		log.info("testSave()");
//		User user = new User("kinga",21,"kinga@gmail.com");
//		
//		user = userService.save(user);
//		
//		log.info(user.toJson());
//		
//		log.info("testSave done!");
//	}
	
	@Test
	public void testRepoSave() throws Exception {
		log.info("testRepoSave()");
		User user = new User("kinga",21,"kinga"+RandomUtils.nextInt(1, Integer.MAX_VALUE)+"@gmail.com");
		
		user = userRepository.saveAndFlush(user);
		
		log.info(user.toJson());
		
		log.info("testSave done!");
	}


}