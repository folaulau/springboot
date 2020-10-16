package com.lovemesomecoding.user;

import java.util.UUID;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovemesomecoding.email.EmailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserDAO      userDAO;

    @Override
    public User getByUuid(String uuid) {
        log.info("getByUuid={}", uuid);
        User user = new User();
        user.setId(1L);
        user.setUuid("mem-" + UUID.randomUUID().toString());
        user.setFirstName("Folau");
        user.setLastName("Kaveinga");
        user.setEmail("folaukaveinga@gmail.com");

        IntStream stream = IntStream.range(0, 3);

        stream.forEach(num -> {
            log.info("num={}", num);
            emailService.sendWelcomeEmail(user);
        });

        userDAO.save(user);

        return user;

    }

}
