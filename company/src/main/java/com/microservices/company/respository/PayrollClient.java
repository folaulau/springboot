package com.microservices.company.respository;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microservices.company.domain.Pay;

@FeignClient("eureka-client-payroll")
public interface PayrollClient {

	@RequestMapping("/api/pay/")
    public Pay getPay();
	
	@RequestMapping(value={"/api/pay/save"}, method=RequestMethod.POST)
    public Pay savePay(Pay pay);
}
