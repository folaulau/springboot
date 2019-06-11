package com.folaukaveinga.api;

import static org.junit.Assert.*;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.folaukaveinga.utils.HttpRequestInterceptor;
import com.folaukaveinga.utils.HttpResponseErrorHandler;
import com.folaukaveinga.utils.ObjectUtils;

public class ApiTester {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	private RestTemplate restTemplate = new RestTemplate(
			new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));

	@Before
	public void setup() {
		restTemplate.getInterceptors().add(new HttpRequestInterceptor());
		restTemplate.setErrorHandler(new HttpResponseErrorHandler());
	}

	@Test
	public void test() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("lang", "to");

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<>(ObjectUtils.toJson(new Object()), headers);

		ResponseEntity<String> response = null;
		String sessionDTO = null;
		try {
			response = restTemplate.exchange(new URI("http://localhost:8080/api?lang=es"), HttpMethod.GET, entity,
					String.class);

			assertEquals(response.getStatusCode(), HttpStatus.OK);

			sessionDTO = response.getBody();

		} catch (Exception e) {
			log.warn("Exception, msg: {}", e.getMessage());
			log.warn(ObjectUtils.toJson(response));
			fail("Failed to create product, msg: " + e.getLocalizedMessage());
		}
		
		log.info("sessionDTO={}",sessionDTO);
	}

}
