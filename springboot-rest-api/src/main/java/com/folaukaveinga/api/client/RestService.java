package com.folaukaveinga.api.client;

import java.net.URI;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.folaukaveinga.api.utility.ApiSessionUtils;
import com.folaukaveinga.api.utility.HttpRequestInterceptor;
import com.folaukaveinga.api.utility.HttpUtils;

@Service
public class RestService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private RestTemplate restTemplate = new RestTemplate(
			new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
	
	
	private String PLACEHOLDER_URL = "https://jsonplaceholder.typicode.com";
	
	public static final String PLACEHOLDER_POST = "posts";
	public static final String PLACEHOLDER_COMMENT = "comments";
	public static final String PLACEHOLDER_ALBUM = "albums";
	public static final String PLACEHOLDER_PHOTO = "photos";
	public static final String PLACEHOLDER_TODO = "todos";
	public static final String PLACEHOLDER_USER = "users";
	

	

	@PostConstruct
	public void init() {
		restTemplate.getInterceptors().add(new HttpRequestInterceptor());
	}

	/**
	 * test data
	 * 
	 * @param uuid
	 * @return boolean
	 */
	@Async
	public CompletableFuture<ArrayNode> getPlaceHolderTestingList(String placeHolder) {
		log.info("getPlaceHolderTestingList(..)");
		String fingerPrint = ApiSessionUtils.getFingerPrint();
		
		log.info("fingerPrint={}",fingerPrint);
		
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<>(new String(), headers);

		ResponseEntity<ArrayNode> response = null;
		ArrayNode result = null;

		try {
			response = restTemplate.exchange(new URI(PLACEHOLDER_URL + "/"+placeHolder), HttpMethod.GET,
					entity, ArrayNode.class);

			result = response.getBody();

			log.info("result: {}", result);

			// log.info("quote: {}", ObjectUtils.toJson(quote));
		} catch (Exception e) {
			log.warn("Exception, msg: {}", e.getMessage());
		}

		return CompletableFuture.completedFuture(result);
	}
	
	public boolean getPlaceHolderTestingData(String placeHolder, long id) {

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<>(new String(), headers);

		ResponseEntity<ObjectNode> response = null;
		ObjectNode result = null;

		try {
			response = restTemplate.exchange(new URI(PLACEHOLDER_URL + "/"+placeHolder+"/"+id), HttpMethod.GET,
					entity, ObjectNode.class);

			result = response.getBody();

			log.warn("result: {}", result);

			return true;

			// log.info("quote: {}", ObjectUtils.toJson(quote));
		} catch (Exception e) {
			log.warn("Exception, msg: {}", e.getMessage());
		}

		return false;
	}
}
