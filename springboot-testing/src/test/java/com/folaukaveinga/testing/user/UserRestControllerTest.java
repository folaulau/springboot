package com.folaukaveinga.testing.user;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.folaukaveinga.testing.user.User;
import com.folaukaveinga.testing.user.UserController;
import com.folaukaveinga.testing.user.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserRestControllerTest {

    @Autowired
    private MockMvc     mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testUserSave() throws Exception {
        User user = new User("kinga", "kaveinga", 21, "kinga@gmail.com");

        User savedUser = new User(1, "kinga", "kaveinga", 21, "kinga@gmail.com");

        when(userService.save(user)).thenReturn(savedUser);

        this.mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(user.toJson()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("kinga")))
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void testGetUserById() throws Exception {
        User mockUser = new User("folau", 21, "fkaveinga@gmail.com");

        when(userService.getById(1)).thenReturn(mockUser);

        this.mockMvc.perform(get("/users/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.firstName", is("folau")))
                .andExpect(jsonPath("$.age", is(21)));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> users = Arrays.asList(new User("folaulau", 21, "folaulau@gmail.com"), new User("kinga", 21, "kinga@gmail.com"));
        when(userService.getAll()).thenReturn(users);

        this.mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("folaulau")))
                .andExpect(jsonPath("$[0].age", is(21)));
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User("kinga", "kaveinga", 21, "kinga@gmail.com");

        User savedUser = new User(1, "kinga", "kaveinga", 21, "kinga@gmail.com");

        when(userService.update(user)).thenReturn(savedUser);

        this.mockMvc.perform(patch("/users/update").contentType(MediaType.APPLICATION_JSON).content(user.toJson()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("kinga")))
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void testRemoveUser() throws Exception {
        long id = 1;
        when(userService.remove(id)).thenReturn(true);

        this.mockMvc.perform(delete("/users/" + id)).andDo(print()).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().string("true"));

    }

}
