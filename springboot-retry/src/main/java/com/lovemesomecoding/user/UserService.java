package com.lovemesomecoding.user;

public interface UserService {

    boolean sendEmail(User user) throws RuntimeException;

    public boolean recover(RuntimeException e, User user);

}
