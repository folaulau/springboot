package com.lovemesomecoding.jms;

import java.time.LocalDateTime;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.lovemesomecoding.mail.Mail;
import com.lovemesomecoding.utils.ObjectUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Receiver {

    @JmsListener(destination = "${queue.sender-receiver}", containerFactory = "jmsFactory")
    public void receiveMessage(Mail mail) {
        log.info("Received mail {}", LocalDateTime.now());
        try {
            log.info("mail={}", ObjectUtils.toJson(mail));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

}
