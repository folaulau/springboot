package com.folaukaveinga.testing.user;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import com.folaukaveinga.testing.user.User;

public class UserUnitTest {

    User user = null;

    @Test
    public void testConstructor() {
        user = new User();
        assertNotNull(user);
    }

    @Test
    public void testConstructorWith3Parameters() {
        user = new User("Jane Doe", 23, "janedoe@gmail.com");
        assertNotNull(user);
    }

    @Test
    public void testConstructorWith4Parameters() {
        user = new User("Jane Doe", 23, "janedoe@gmail.com");
        assertNotNull(user);
    }

    @Test
    public void testIdSetter() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        long id = 1;
        user = new User();
        user.setId(id);
        Field field = user.getClass().getDeclaredField("id");
        field.setAccessible(true);
        assertEquals(field.get(user), id);
    }

    @Test
    public void testIdGetter() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        long id = 1;
        user = new User();
        user.setId(id);
        assertEquals(user.getId().longValue(), id);
    }

    @Test
    public void testFirstNameSetter() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String firstName = "John";
        user = new User();
        user.setFirstName(firstName);
        Field field = user.getClass().getDeclaredField("firstName");
        field.setAccessible(true);
        assertEquals(field.get(user), firstName);
    }

    @Test
    public void testNameGetter() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String name = "John Doe";
        user = new User();
        user.setLastName(name);
        assertEquals(user.getLastName(), name);
    }

    @Test
    public void testAgeGetter() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        int age = 0;
        user = new User();
        assertEquals(user.getAge(), 0);

        age = 23;
        user = new User();
        user.setDob(DateUtils.addYears(new Date(), -age));
        assertEquals(user.getAge(), age);
    }

    @Test
    public void testEmailSetter() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String email = "jdoe@gmail.com";
        user = new User();
        user.setEmail(email);
        Field field = user.getClass().getDeclaredField("email");
        field.setAccessible(true);
        assertEquals(field.get(user), email);
    }

    @Test
    public void testEmailGetter() {
        String email = "jdoe@gmail.com";
        user = new User();
        user.setEmail(email);
        assertEquals(user.getEmail(), email);
    }

}
