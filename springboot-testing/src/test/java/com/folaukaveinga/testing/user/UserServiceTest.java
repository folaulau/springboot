package com.folaukaveinga.testing.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.folaukaveinga.testing.plan.Plan;
import com.folaukaveinga.testing.user.User;
import com.folaukaveinga.testing.user.UserRepository;
import com.folaukaveinga.testing.user.UserService;
import com.folaukaveinga.testing.utility.ConstantUtils;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private final Logger         log            = LoggerFactory.getLogger(this.getClass());

    @InjectMocks
    private UserService          userService    = new UserServiceImp();

    @Spy
    private UserService          spyUserService = new UserServiceImp();

    @Mock
    private UserNtcService       userNtcService;

    @Mock
    private UserDAO              userDAO;

    /*
     * Mockito ArgumentCaptor is used to capture arguments for mocked methods. ArgumentCaptor is used with Mockito
     * verify() methods to get the arguments passed when any method is called. This way, we can provide additional JUnit
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

        /**
         * thenReturn or doReturn() are used to specify a value to be returned <br/>
         * upon method invocation.
         */
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

        log.info("testUpdate() - passed!\n\n");
    }

    @Test
    public void testSaveWithException() {
        log.info("testSaveWithException()");
        User user = ConstantUtils.generateUser();
        user.setEmail(null);

        when(userDAO.save(user)).thenThrow(new RuntimeException("An error occurred"));

        userService.save(user);

        verify(userDAO).save(same(user));

        log.info("testSaveWithException() - passed!");
    }

    @Test
    public void testGetDOB() throws Exception {
        log.info("testGetDOB()");

        int age = userService.getAge(LocalDate.now().plusYears(-5));

        log.info("age={}", age);

        log.info("testGetDOB() - passed!");
    }

    @Test
    public void test_SignUpWithPlanAndSpy() throws Exception {
        log.info("test_SignUpWithPlanAndSpy()");
        User user = null;
        double planAmount = 200.0;
        Plan plan = new Plan(1L, planAmount, user);

        planAmount = 500.0;
        Plan savedPlan = new Plan(1L, planAmount, user);

        // This call is not actually made. Execution flow does not get within the signUpForPlan(check the log within the
        // method)
        lenient().doReturn(savedPlan).when(spyUserService).signUpForPlan(plan);

        assertThat(savedPlan.getAmount()).isEqualTo(planAmount);

        log.info("test_SignUpWithPlanAndSpy() - passed!");

        List<String> spyList = spy(new ArrayList());

        spyList.add("one");
        verify(spyList).add("one");

        log.info("spyList size: {}", spyList.size());
    }

    /**
     * The spy object will wrap an existing instance. It will still behave in the <br/>
     * same way as the normal instance – the only difference is that it will also <br/>
     * be instrumented to track all the interactions with it.
     */
    @Test
    public void test_Spy() throws Exception {
        log.info("test_Spy()");

        List<String> spyList = spy(new ArrayList());

        spyList.add("one");
        verify(spyList).add("one");

        assertThat(spyList.size()).isEqualTo(1);

        log.info("spyList size: {}", spyList.size());
    }

    /**
     * When Mockito creates a mock – it does so from the Class of a Type, <br/>
     * not from an actual instance. The mock simply creates a bare-bones shell <br/>
     * instance of the Class, entirely instrumented to track interactions with it.<br/>
     */
    @Test
    public void test_Mock() throws Exception {
        log.info("test_Mock()");

        List<String> mockList = mock(ArrayList.class);

        mockList.add("one");
        verify(mockList).add("one");

        assertThat(mockList.size()).isEqualTo(1);

        log.info("spyList size: {}", mockList.size());
    }

}
