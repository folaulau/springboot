package com.folaukaveinga.testing.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.folaukaveinga.testing.user.User;
import com.folaukaveinga.testing.utility.ConstantUtils;
import com.folaukaveinga.testing.utility.ObjectUtils;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDAOImpTest {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserDAO userDAO;

	@Before
	public void setup() {

		for (int i = 0; i < 5; i++) {
			User user = ConstantUtils.generateUser();
			user = userDAO.save(user);
		}
	}

	@Transactional
	@Test
	public void testGetEmailById() throws Exception {
		long id = 1;

		String email = userDAO.getEmailById(id);

		log.info("email={}", email);

		assertNotNull(email);

		id = 100;

		email = userDAO.getEmailById(id);

		log.info("email={}", email);

		assertEquals(null, email);

		System.out.println("\n");
	}

	@Transactional
	@Test
	public void testGetIdByEmail() throws Exception {

		User user = ConstantUtils.generateUser();
		user = userDAO.save(user);

		log.info("user={}", ObjectUtils.toJson(user));

		String email = user.getEmail();

		long id = userDAO.getIdByEmail(email);

		log.info("id={}", id);

		System.out.println("\n");
	}

}