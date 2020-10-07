package com.lovemesomecoding.user;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(value = "users", produces = "Rest API for user operations", tags = "User Controller")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Send Welcome Email")
    @PostMapping("/users/welcome/email")
    public ResponseEntity<Boolean> sendWelcomeEmail(@RequestBody User user) {
        log.info("sendWelcomeEmail , {}", user.toString());
        boolean sent = userService.sendEmail(user);
        log.info("sent , {}", sent);
        return new ResponseEntity<>(sent, HttpStatus.OK);
    }

}
