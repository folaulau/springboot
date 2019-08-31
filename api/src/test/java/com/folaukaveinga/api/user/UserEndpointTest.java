package com.folaukaveinga.api.user;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.folaukaveinga.api.utility.HttpRequestInterceptor;
import com.folaukaveinga.api.utility.HttpUtils;

public class UserEndpointTest {

	RestTemplate restTemplate = new RestTemplate(
			new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));

	private String apiUrl = "";
	
	@Before
	public void init() {
		restTemplate.getInterceptors().add(new HttpRequestInterceptor());
	}
	
	@Test
	public void create() {
		User user = null;
		String username = "";
		String password = "";
		// include headers
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", HttpUtils.generateBasicAuthenticationToken(username, password));
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		// include payload and headers
		HttpEntity<String> entity = new HttpEntity<>(user.toJson(), headers);
		restTemplate.postForObject(apiUrl+"/users", entity, User.class);
	}

	@Test
	public void update() {
		User user = null;
		try {
			restTemplate.put(new URI(apiUrl+"/users"), user);
		} catch (RestClientException | URISyntaxException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	@Test
	public void getById(Integer id) {
		Map<String, Integer> params = new HashMap<String, Integer>();
	    params.put("id", id);
	    restTemplate.getForObject(apiUrl+"/users/{id}", User.class, params);
	}

	@Test
	public void getAll() {
		List<User> users = restTemplate.getForObject(apiUrl+"/users", List.class);
	}
}
