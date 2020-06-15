package com.folaukaveinga.testing.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.folaukaveinga.testing.user.User;
import com.folaukaveinga.testing.user.UserRepository;
import com.folaukaveinga.testing.utility.ConstantUtils;
import com.folaukaveinga.testing.utility.ObjectUtils;
import com.folaukaveinga.testing.utility.RandomGeneratorUtils;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    private final Logger   log       = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    List<User>             testUsers = new ArrayList<User>();

    @Before
    public void setup() {

        for (int i = 0; i < 7; i++) {
            User user = ConstantUtils.generateUser();
            user = userRepository.saveAndFlush(user);
            testUsers.add(user);
            // log.info("user={}",ObjectUtils.toJson(user));
        }

        System.out.println("\n");
    }

    @Transactional
    @Test
    public void testSaveUser() {
        User user = ConstantUtils.generateUser();
        log.info("user={}", ObjectUtils.toJson(user));

        User savedUser = userRepository.saveAndFlush(user);
        log.info("savedUser={}", ObjectUtils.toJson(savedUser));

        assertNotNull(savedUser);

        log.info("savedUser==user -> {}", savedUser == user);

        assertEquals(user, savedUser);

        long count = userRepository.count();

        log.info("count={}", count);

        assertEquals(8, count);

        log.info("\n\ntestSave passed\n");
    }

    // @Transactional
    // @Test
    // public void testFindByAge() throws InterruptedException, ExecutionException {
    // log.info("testFindByAge({})", age);
    // List<User> savedUsers = userRepository.findByAge(age);
    // assertNotNull(savedUsers);
    // assertNotEquals(savedUsers.size(), 0);
    //
    // savedUsers.forEach((user) -> {
    // System.out.println(user.toString());
    // });
    //
    // log.info("\n\ntestFindByAge passed\n");
    // }

    @Transactional
    @Test
    public void testFindByName() throws InterruptedException, ExecutionException {
        log.info("testFindByName({})", testUsers.get(0).getFirstName());

        String lastName = testUsers.get(0).getLastName();
        List<User> savedUsers = userRepository.findByLastName(lastName);

        log.info(savedUsers.toString());

        assertNotNull(savedUsers);
        assertNotEquals(savedUsers.size(), 0);

        log.info("\n\ntestFindByName passed\n");
    }

    @Transactional
    @Test
    public void testFindByEmail() throws InterruptedException, ExecutionException {
        log.info("testFindByEmail({})", testUsers.get(0).getEmail());
        User savedUser = userRepository.findByEmail(testUsers.get(0).getEmail());

        assertNotNull(savedUser);
        assertNotEquals(savedUser.getId().longValue(), 0);

        System.out.println(savedUser.toString());

        log.info("\n\ntestFindByEmail passed\n");

    }

}
