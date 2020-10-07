package com.lovemesomecoding.user;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImp implements UserService {

    @Retryable(value = {RuntimeException.class}, maxAttempts = 3, backoff = @Backoff(delay = 2000))
    @Override
    public boolean sendEmail(User user) throws RuntimeException {
        log.info("send email");
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
