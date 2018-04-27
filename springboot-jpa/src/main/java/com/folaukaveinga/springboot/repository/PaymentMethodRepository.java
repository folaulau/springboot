package com.folaukaveinga.springboot.repository;

import org.springframework.data.repository.CrudRepository;

import com.folaukaveinga.springboot.domain.PaymentMethod;

public interface PaymentMethodRepository <T extends PaymentMethod> extends CrudRepository<T, Long>{
	
}
