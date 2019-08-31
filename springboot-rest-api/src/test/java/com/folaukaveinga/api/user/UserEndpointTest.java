package com.folaukaveinga.api.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.folaukaveinga.api.utility.HttpRequestInterceptor;
import com.folaukaveinga.api.utility.HttpUtils;
import com.folaukaveinga.api.utility.ObjectUtils;

public class UserEndpointTest {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	RestTemplate restTemplate = new RestTemplate(
			new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));

	private String apiUrl = "http://localhost:8080";

	@Before
	public void init() {
		log.info("init()");
		restTemplate.getInterceptors().add(new HttpRequestInterceptor());
	}

	@Test
	public void testCreate() {
		log.info("create(..)");
		User user = new User();
		user.setEmail("folau@gmail.com");
		user.setName("Laulau");
		user.setType("user");

		String username = "asdf";
		String password = "asdf";
		// include headers
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", HttpUtils.generateBasicAuthenticationToken(username, password));
		headers.setContentType(MediaType.APPLICATION_JSON);

		// include payload and headers
		HttpEntity<String> entity = new HttpEntity<>(user.toJson(), headers);
		User createdUser = restTemplate.postForObject(apiUrl + "/users", entity, User.class);

		log.info("createdUser={}", ObjectUtils.toJson(createdUser));
	}

	@Test
	public void testPostObjWithFileUsingRequestpart() {
		User user = new User();
		user.setEmail("folau@gmail.com");
		user.setName("Laulau");
		user.setType("user");

		File file = new File("kobe.jpeg");
		log.info("createExpenseByMember()");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", new FileSystemResource(file));
		body.add("user", user);

		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

		try {
			User createdUser = restTemplate.postForObject(apiUrl + "/files/withrequestpart", entity, User.class);

			log.info("createdUser={}", ObjectUtils.toJson(createdUser));

			log.info("upload w9 form done!");

		} catch (Exception e) {
			log.warn("Exception, msg: {}", e.getMessage());
			fail("failed to upload w9 form!");
		}

	}

	/**
	 * This does not work!
	 */
	@Test
	public void testPostObjWithFileUsingPOJO() {

		File file = new File("kobe.jpeg");
		log.info("createExpenseByMember()");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		UserCreateDTO userCreateDTO = new UserCreateDTO();

		userCreateDTO.setEmail("folau@gmail.com");
		userCreateDTO.setName("Folau");
		
		try {
			byte[] bytesArray = new byte[(int) file.length()];

			FileInputStream fis = new FileInputStream(file);
			fis.read(bytesArray); // read file into bytes[]
			fis.close();
			
			userCreateDTO.setFile(new MockMultipartFile(file.getName(), bytesArray));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		HttpEntity<String> entity = new HttpEntity<>(ObjectUtils.toJson(userCreateDTO), headers);

		// HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body,
		// headers);

		try {
			User createdUser = restTemplate.postForObject(apiUrl + "/files/withdto", entity, User.class);

			log.info("createdUser={}", ObjectUtils.toJson(createdUser));

			log.info("upload w9 form done!");

		} catch (Exception e) {
			log.warn("Exception, msg: {}", e.getMessage());
			fail("failed to upload w9 form!");
		}

	}

	
	/**
	 * This works!
	 */
	@Test
	public void testPostObjWithFileUsingPOJOAndMultiPart() {

		File file = new File("kobe.jpeg");
		log.info("createExpenseByMember()");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", new FileSystemResource(file));
		body.add("name", "Folau");
		body.add("email", "folau@gmail.com");

		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

		try {
			User createdUser = restTemplate.postForObject(apiUrl + "/files/withdto", entity, User.class);

			log.info("createdUser={}", ObjectUtils.toJson(createdUser));

			log.info("upload w9 form done!");

		} catch (Exception e) {
			log.warn("Exception, msg: {}", e.getMessage());
			fail("failed to upload w9 form!");
		}

	}

}
