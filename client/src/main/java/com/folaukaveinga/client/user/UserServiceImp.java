package com.folaukaveinga.client.user;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Service
public class UserServiceImp implements UserService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private RestTemplate restTemplate = new RestTemplate();
	
	private String apiUrl = "http://localhost:8080";
	
	@Override
	public User create(User user) {
		return restTemplate.postForObject(apiUrl+"/users", user, User.class);
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
