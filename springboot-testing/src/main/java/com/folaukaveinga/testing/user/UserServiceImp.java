package com.folaukaveinga.testing.user;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.folaukaveinga.testing.plan.Plan;

@Service
public class UserServiceImp implements UserService {

    private final Logger         log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserValidatorService userValidatorService;

    @Autowired
    private UserDAO              userDAO;

    @Autowired
    private UserNtcService       userNtcService;

    @Override
    public User save(User user) {
        log.info("save(..)");
        try {
            user = userDAO.save(user);
        } catch (RuntimeException re) {
            log.error(re.getMessage());
        }
        return user;
    }

    @Override
    public User getById(long id) {
        log.info("get user by id: {}", id);

        User user = userDAO.getById(id);

        UserUtils.validateUser(user, userValidatorService);

        return user;
    }

    @Override
    public User getByEmail(String email) {
        log.info("get user by email: {}", email);
        return userDAO.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public User update(User user) {
        user.setUpdatedAt(new Date());
        return userDAO.save(user);
    }

    @Override
    public boolean remove(long id) {
        return userDAO.deleteById(id);
    }

    @Override
    public int getAge(LocalDate dob) {
        log.info("getAge(..)");
        return Period.between(dob, LocalDate.now()).getYears();
    }

    @Override
    public User signUp(User user) {
        log.info("signUp(..)");
        user = this.save(user);

        boolean emailSent = this.userNtcService.sendWelcomeEmail(user);

        log.info("welcome email sent: {}", emailSent);

        return user;
    }

    @Override
    public List<User> getByLastName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Plan signUpForPlan(Plan plan) {
        // TODO Auto-generated method stub
        return null;

    }

}
