package com.folaukaveinga.client.user;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.folaukaveinga.client.util.HttpRequestInterceptor;
import com.folaukaveinga.client.util.HttpUtils;


@Service
public class UserServiceImp implements UserService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
	
	private String apiUrl = "http://localhost:8080";
	
	private String username = "username";

	private String password = "password";
	
	@Override
	public User create(User user) {
		
		// include headers
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", HttpUtils.basicAuthentication(username, password));
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		// include payload and headers
		HttpEntity<String> entity = new HttpEntity<>(user.toJson(), headers);
		restTemplate.setInterceptors(Collections.singletonList(new HttpRequestInterceptor()));
		return restTemplate.postForObject(apiUrl+"/users", entity, User.class);
	}

	@Override
	public User update(User user) {
		try {
			restTemplate.put(new URI(apiUrl+"/users"), user);
		} catch (RestClientException | URISyntaxException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return user;
	}

	@Override
	public User getById(Integer id) {
		Map<String, Integer> params = new HashMap<String, Integer>();
	    params.put("id", id);
	    return restTemplate.getForObject(apiUrl+"/users/{id}", User.class, params);
	}

	@Override
	public List<User> getAll() {
		return restTemplate.getForObject(apiUrl+"/users", List.class);
	}

}
