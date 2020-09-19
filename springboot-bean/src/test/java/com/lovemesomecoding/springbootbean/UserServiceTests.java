package com.lovemesomecoding.springbootbean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lovemesomecoding.model.User;
import com.lovemesomecoding.service.UserService;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    public void test_ValidSave() {
        User user = new User();
        user.setFirstName("Folau");
        user.setLastName("Kaveinga");

        userService.save(user);
    }

}
