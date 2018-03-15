package com.folaukaveinga.testing.unit;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.folaukaveinga.testing.domain.User;
import com.folaukaveinga.testing.rest.UserRestController;
import com.folaukaveinga.testing.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserRestController.class)
public class UserRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;
	
	@Test
	public void testSave() throws Exception {
		User user = new User("kinga",21,"kinga@gmail.com");
		User savedUser = new User(1, "kinga",21,"kinga@gmail.com");
		when(userService.save(user)).thenReturn(savedUser);
		
		this.mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).content(user.toJson()))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name", is("kinga")))
			.andExpect(jsonPath("$.id", is(1)));
	}

	@Test
	public void testGet() throws Exception {
		when(userService.get(1)).thenReturn(new User("folau",21,"fkaveinga@gmail.com"));
		
		this.mockMvc.perform(get("/api/users/1").contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name", is("folau")))
			.andExpect(jsonPath("$.age", is(21)));
	}
	
	@Test
	public void testGetAll() throws Exception {
		List<User> users = Arrays.asList(
				new User("folaulau",21,"folaulau@gmail.com"),
				new User("kinga",21,"kinga@gmail.com"));
		when(userService.getAll()).thenReturn(users);
		
		this.mockMvc.perform(get("/api/users").contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].name", is("folaulau")))
			.andExpect(jsonPath("$[0].age", is(21)));
	}
	
	@Test
	public void testUpdate() throws Exception {
		User user = new User("kinga",21,"kinga@gmail.com");
		User savedUser = new User(1, "kinga",21,"kinga@gmail.com");
		when(userService.update(user)).thenReturn(savedUser);
		
		this.mockMvc.perform(patch("/api/users/update").contentType(MediaType.APPLICATION_JSON).content(user.toJson()))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name", is("kinga")))
			.andExpect(jsonPath("$.id", is(1)));
	}
	
	@Test
	public void testRemove() throws Exception {
		long id = 1;
		when(userService.remove(id)).thenReturn(true);
		
		this.mockMvc.perform(delete("/api/users/remove/1"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("true"));
		
	}

}