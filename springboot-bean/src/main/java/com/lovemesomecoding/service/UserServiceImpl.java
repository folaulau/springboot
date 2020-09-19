package com.lovemesomecoding.service;

import org.springframework.stereotype.Service;

import com.lovemesomecoding.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User save(User user) {
        log.info("save,  user={}", user.toString());
        return user;
    }

}
