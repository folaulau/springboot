package com.lovemesomecoding.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(value = "users", produces = "Rest API for User operations", tags = "User Controller")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Get Profile By Uuid")
    @GetMapping("/{uuid}/profile")
    public ResponseEntity<User> getProfileByUuid(@PathVariable("uuid") String uuid) {
        log.info("get user profile by uuid: {}", uuid);

        User user = userService.getByUuid(uuid);

        log.info(user.toString());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
