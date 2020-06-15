package com.folaukaveinga.testing.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.folaukaveinga.testing.user.User;
import com.folaukaveinga.testing.user.UserRepository;
import com.folaukaveinga.testing.user.UserService;
import com.folaukaveinga.testing.utility.ConstantUtils;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@InjectMocks
	private UserService userService = new UserServiceImp();

	@Mock
	private UserNtcService userNtcService;

	@Mock
	private UserDAO userDAO;

	/*
	 * Mockito ArgumentCaptor is used to capture arguments for mocked methods.
	 * ArgumentCaptor is used with Mockito verify() methods to get the arguments
	 * passed when any method is called. This way, we can provide additional JUnit
	 * assertions for our tests
	 */
	@Captor
	private ArgumentCaptor<User> userCaptor;

	@Before
	public void setup() {
		log.info("setUp()");
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSave() throws Exception {
		log.info("testSave()");
		User user = ConstantUtils.generateUser();

		log.info("user={}", user.toJson());

		when(userDAO.save(any(User.class))).thenReturn(user);

		User savedUser = userService.save(user);

		verify(userDAO).save(same(user));

		assertEquals(user, savedUser);

		log.info("testSave() - passed\n\n");
	}

	@Test
	public void testSignUp() throws Exception {
		log.info("testSignUp()");
		User user = ConstantUtils.generateUser();

		log.info(user.toJson());

		when(userDAO.save(any(User.class))).thenReturn(user);

		when(userNtcService.sendWelcomeEmail(any(User.class))).thenReturn(true);

		User signedUpUser = userService.signUp(user);

		InOrder inOrder = Mockito.inOrder(userDAO, userNtcService);

		inOrder.verify(userDAO, times(1)).save(userCaptor.capture());

		assertEquals(user, signedUpUser);

		log.info("user captor email={}", userCaptor.getValue().getEmail());

		assertThat(userCaptor.getValue()).isSameAs(signedUpUser);

		log.info("testSignUp() - passed\n\n");
	}

	@Test
    public void testUpdateUser() throws Exception {
		log.info("testUpdate()");
		User user = ConstantUtils.generateUser();

		when(userDAO.save(user)).thenReturn(user);

		User updatedUser = userService.update(user);

        log.info(updatedUser.toJson());

		verify(userDAO).save(same(user));

		log.info("testUpdate()\n\n");
	}

	@Test
	public void testSaveWithException() {
		log.info("testRepoSave()");
		User user = ConstantUtils.generateUser();
		user.setEmail(null);

		when(userDAO.save(user)).thenThrow(new RuntimeException("An error occurred"));

		userService.save(user);

		verify(userDAO).save(same(user));
	}

	@Test
	public void testGetDOB() throws Exception {
		log.info("testGetDOB()");

		int age = userService.getAge(LocalDate.now().plusYears(-5));

		log.info("age={}", age);
	}

}