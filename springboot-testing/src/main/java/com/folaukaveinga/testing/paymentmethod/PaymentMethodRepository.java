package com.folaukaveinga.testing.paymentmethod;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    List<PaymentMethod> findByUserId(Long userId);

    long countByUserId(long userId);
}
