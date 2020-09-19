package com.lovemesomecoding.jms;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.lovemesomecoding.mail.Mail;
import com.lovemesomecoding.mail.MailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Sender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${queue.sender-receiver}")
    private String      queue;

    public void sendMail(Mail mail) {
        try {
            jmsTemplate.convertAndSend(queue, mail);
            log.info("Mail sent to queue, {}, at {}", queue, LocalDateTime.now());
        } catch (Exception e) {
            log.error("Mail not sent");
            log.error("Exception, msg: {}", e.getMessage());
        }

    }

}
