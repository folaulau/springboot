package com.folaukaveinga.testing.paymentmethod;

import java.util.List;

public interface PaymentMethodService {

    PaymentMethod add(PaymentMethod paymenthod);

    List<PaymentMethod> getByMemberId(Long memberId);

}
