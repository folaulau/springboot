package com.microservices.facebook.config;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("eureka-client-twitter")
public interface FeignConfig {

}
