package com.folaukaveinga.testing.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.folaukaveinga.testing.paymentmethod.PaymentMethod;

@Service
public class UserNtcServiceImp implements UserNtcService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean sendWelcomeEmail(User user) {
		log.info("sendWelcomeEmail(..)");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("welcome email sent to {}", user.getEmail());
		return true;
	}

    @Override
    public boolean notifyUserOfNewPaymentMethod(PaymentMethod paymentMethod) {
        // TODO Auto-generated method stub
        return false;

    }
	
	
}
