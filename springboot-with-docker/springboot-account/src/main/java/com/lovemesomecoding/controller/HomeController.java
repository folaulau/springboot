package com.lovemesomecoding.controller;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HomeController {

    private RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));

    @GetMapping("/")
    public ResponseEntity<String> home() {
        log.debug("welcome to home of springboot account");

        return new ResponseEntity<>("welcome to home of springboot account", HttpStatus.OK);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        log.debug("hello from springboot account");

        return new ResponseEntity<>("hello from springboot account", HttpStatus.OK);
    }

    @GetMapping("/say-hi-to-billing")
    public ResponseEntity<String> helloAccount() {
        log.debug("saying hello to springboot billing");

        restTemplate.getInterceptors().add(new HttpRequestInterceptor());

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(new String(), headers);
        try {

            ResponseEntity<String> response = restTemplate.exchange(new URI("http://localhost:9999/bill/hello"), HttpMethod.GET, entity, String.class);

            log.info("saying hi got back.");

            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);

        } catch (Exception e) {
            log.warn("Exception, msg: {}", e.getMessage());
        }

        return new ResponseEntity<>("hello from springboot account", HttpStatus.OK);
    }

}
