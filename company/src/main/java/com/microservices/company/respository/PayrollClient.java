package com.microservices.company.respository;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.microservices.company.domain.Pay;

@FeignClient("eureka-client-payroll")
public interface PayrollClient {

	@RequestMapping("/api/pay/")
    public Pay getPay();
}
