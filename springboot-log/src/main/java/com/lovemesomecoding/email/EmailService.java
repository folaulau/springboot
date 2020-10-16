package com.lovemesomecoding.email;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.lovemesomecoding.user.User;
import com.lovemesomecoding.utils.ThreadPoolNames;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailService {

    @Async(value = ThreadPoolNames.MainThreadPool)
    public void sendWelcomeEmail(User user) {
        // ThreadContext.put("memberUuid", user.getUuid());
        log.info("sendWelcomeEmail to " + user.toString());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        log.info("email sent!");

        // ThreadContext.clearMap();
    }

}
