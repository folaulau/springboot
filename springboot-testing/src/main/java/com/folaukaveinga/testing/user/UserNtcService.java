package com.folaukaveinga.testing.user;

import com.folaukaveinga.testing.paymentmethod.PaymentMethod;

public interface UserNtcService {

	boolean sendWelcomeEmail(User user);

    boolean notifyUserOfNewPaymentMethod(PaymentMethod paymentMethod);
}
