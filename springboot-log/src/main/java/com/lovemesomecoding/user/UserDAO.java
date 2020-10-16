package com.lovemesomecoding.user;

import org.springframework.stereotype.Repository;

import com.lovemesomecoding.email.EmailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserDAO {

    public User save(User user) {
        log.info("user=", user.toString());
        return user;
    }

}
