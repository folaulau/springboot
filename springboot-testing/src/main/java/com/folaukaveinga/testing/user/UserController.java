package com.folaukaveinga.testing.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "users", produces = "Rest API for User operations", tags = "User Controller")
@RestController
@RequestMapping("/users")
public class UserController {

    private Logger      log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Get Member By Id")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@ApiParam(name = "id", required = true, value = "id of member") @PathVariable("id") Long id) {
        log.info("get user by id: {}", id);

        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        log.info("create user: {}", user.toJson());
        User savedUser = userService.save(user);
        log.info("savedUser: {}", savedUser.toJson());
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        log.info("get all users");
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user) {
        log.info("patching user: " + user.toString());
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable long id) {
        log.info("remove user with id: {}", id);
        return new ResponseEntity<>(userService.remove(id), HttpStatus.OK);
    }

}
