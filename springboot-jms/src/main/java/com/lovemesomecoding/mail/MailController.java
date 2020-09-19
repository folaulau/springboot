package com.lovemesomecoding.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lovemesomecoding.jms.Receiver;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(value = "users", produces = "Rest API for Mail operations", tags = "Mail Controller")
@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping(value = {"/send-mail/{message}"})
    public ResponseEntity<Boolean> sendMail(@RequestBody Mail mail) {
        log.info("sendMail(..)");
        return new ResponseEntity<>(mailService.sendMail(mail), HttpStatus.OK);
    }

}
