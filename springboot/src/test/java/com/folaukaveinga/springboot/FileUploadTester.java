package com.folaukaveinga.springboot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.folaukaveinga.springboot.domain.User;
import com.folaukaveinga.springboot.utility.HttpRequestInterceptor;

public class FileUploadTester {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private RestTemplate restTemplate = new RestTemplate(
			new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));

	@Before
	public void init() {
		restTemplate.getInterceptors().add(new HttpRequestInterceptor());
	}

	/*
	 * Not working
	 */
	@Test
	public void testUploadSingleFileWithJsonObject() {
		User user = new User();
		user.setName("Folau");
		user.setAge(3);
		user.setId(5);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		File receiptFile = null;

		try {
			String fileName = "superman.jpeg";
			ClassPathResource classPathResource = new ClassPathResource("/" + fileName);
			receiptFile = new File(classPathResource.getFilename());
			FileUtils.copyInputStreamToFile(classPathResource.getInputStream(), receiptFile);

			FileInputStream input = new FileInputStream(receiptFile);
			MultipartFile multipartFile = new MockMultipartFile("file", receiptFile.getName(), "text/plain",
					IOUtils.toByteArray(input));
			user.setFile(multipartFile);
		} catch (IOException e) {
		}

		if (receiptFile == null) {
			System.out.println("Receipt File is null");
			return;
		}

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//
//		body.add("file", new FileSystemResource(receiptFile));
		body.add("user", user);

		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

		ResponseEntity<ObjectNode> response = null;
		ObjectNode receipts = null;
		try {
			response = restTemplate.postForEntity(
					new URI("http://localhost:8082/api/files/uploadSingleFileWithJsonObject"), entity,
					ObjectNode.class);

			assertEquals(response.getStatusCode(), HttpStatus.OK);

			receipts = response.getBody();

			log.info("uploading receipt done!");

		} catch (Exception e) {
			log.warn("Exception, msg: {}", e.getMessage());
			fail("Failed to upload receipt");
		}
	}

	/*
	 * Reference:
	 * https://stackoverflow.com/questions/49845355/spring-boot-controller-upload-
	 * multipart-and-json-to-dto
	 * 
	 * Working
	 */
	@Test
	public void testUploadWithJsonFileData() {
		User user = new User();
		user.setName("Folau");
		user.setName("Folau");
		user.setAge(3);
		user.setId(5);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		File receiptFile = null;

		try {
			String fileName = "superman.jpeg";
			ClassPathResource classPathResource = new ClassPathResource("/" + fileName);
			receiptFile = new File(classPathResource.getFilename());
			FileUtils.copyInputStreamToFile(classPathResource.getInputStream(), receiptFile);
		} catch (IOException e) {
		}

		if (receiptFile == null) {
			System.out.println("Receipt File is null");
			return;
		}

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

		body.add("file", new FileSystemResource(receiptFile));
		body.add("user", user);

		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

		ResponseEntity<ObjectNode> response = null;
		ObjectNode receipts = null;
		try {
			response = restTemplate.postForEntity(new URI("http://localhost:8082/api/files/uploadWithJsonObject"),
					entity, ObjectNode.class);

			assertEquals(response.getStatusCode(), HttpStatus.OK);

			receipts = response.getBody();

			log.info("uploading receipt done!");

		} catch (Exception e) {
			log.warn("Exception, msg: {}", e.getMessage());
			fail("Failed to upload receipt");
		}
	}

	@Test
	public void testUploadMultipleFilesWithJsonFileData() {
		User user = new User();
		user.setName("Folau");
		user.setName("Folau");
		user.setAge(3);
		user.setId(5);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		File receiptFile = null;

		try {
			String fileName = "superman.jpeg";
			ClassPathResource classPathResource = new ClassPathResource("/" + fileName);
			receiptFile = new File(classPathResource.getFilename());
			FileUtils.copyInputStreamToFile(classPathResource.getInputStream(), receiptFile);
		} catch (IOException e) {
		}

		if (receiptFile == null) {
			System.out.println("Receipt File is null");
			return;
		}

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

		List<FileSystemResource> files = Arrays.asList(new FileSystemResource(receiptFile),
				new FileSystemResource(receiptFile));

		body.add("files", new FileSystemResource(receiptFile));
		body.add("files", new FileSystemResource(receiptFile));
		body.add("user", user);

		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

		ResponseEntity<ArrayNode> response = null;
		ArrayNode attachments = null;
		try {
			response = restTemplate.postForEntity(
					new URI("http://localhost:8082/api/files/uploadMultipleFilesWithJsonObject"), entity,
					ArrayNode.class);

			assertEquals(response.getStatusCode(), HttpStatus.OK);

			attachments = response.getBody();

			log.info("uploading receipt done!");

		} catch (Exception e) {
			log.warn("Exception, msg: {}", e.getMessage());
			fail("Failed to upload receipt");
		}
	}

}
