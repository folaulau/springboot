package com.lovemesomecoding;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lovemesomecoding.user.User;
import com.lovemesomecoding.user.UserRepository;
import com.lovemesomecoding.utils.ObjectUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTester {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	@Test
	public void getAllUsers() {
		List<User> users = userRepository.getAllUser();

		users.forEach((user) -> {
			log.info("user={}", ObjectUtils.toJson(user));
		});
	}

	@Test
	public void depositBalance() {
		String userIdA = "user-02535ec8-257a-4870-980c-e596b43d43f6-SSZJyXbtIV";
		String userIdB = "user-61ea3064-9074-4724-9956-66f0623ade9b-SXjVYUDmZZ";
		
		User userABefore = userRepository.getById(userIdA);
		
		log.info("userA={}", ObjectUtils.toJson(userABefore));
		
		User userBBefore = userRepository.getById(userIdB);
		
		log.info("userB={}", ObjectUtils.toJson(userBBefore));
		
		double amount = 20.0;

		boolean result = userRepository.tranferBalance(amount, userABefore, userBBefore);

		log.info("result={}", result);
		
		User userAAfter = userRepository.getById(userIdA);
		
		User userBAfter = userRepository.getById(userIdB);
		
		log.info("userABefore balance={}, userAAfter balance={}", userABefore.getBalance(), userAAfter.getBalance());
		
		log.info("userBBefore balance={}, userBAfter balance={}", userBBefore.getBalance(), userBAfter.getBalance());
		
	}

}
