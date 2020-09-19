package com.lovemesomecoding.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lovemesomecoding.service.UserService;
import com.lovemesomecoding.service.UserServiceImpl;

@Configuration
public class BeanConfig {

    @Bean
    public UserService userService() {
        UserService userService = new UserServiceImpl();
        return userService;
    }

}
