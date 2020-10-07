package com.lovemesomecoding.user;

import java.time.LocalDateTime;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImp implements UserService {

    @Retryable(value = {RuntimeException.class}, maxAttempts = 5, backoff = @Backoff(delay = 2000, multiplier = 2))
    @Override
    public boolean sendEmail(User user) throws RuntimeException {
        log.info("sending email at {}", LocalDateTime.now().toString());
        if (true) {
            throw new RuntimeException("error");
        }

        return false;
    }

    @Recover
    @Override
    public boolean recover(RuntimeException e, User user) {
        log.info("recover");
        return true;
    }

}
