package com.folaukaveinga.testing.paymentmethod;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.folaukaveinga.testing.user.UserNtcService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentMethodServiceImp implements PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private UserNtcService          userNtcService;

    private PaymentMethod save(PaymentMethod paymenthod) {
        // TODO Auto-generated method stub
        return paymentMethodRepository.saveAndFlush(paymenthod);

    }

    @Override
    public PaymentMethod add(PaymentMethod paymentMethod) {
        // TODO Auto-generated method stub
        paymentMethod = this.save(paymentMethod);

        userNtcService.notifyUserOfNewPaymentMethod(paymentMethod);

        return paymentMethod;

    }

    @Override
    public List<PaymentMethod> getByMemberId(Long memberId) {
        // TODO Auto-generated method stub
        return paymentMethodRepository.findByUserId(memberId);

    }

}
