package com.folaukaveinga.testing.paymentmethod;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PaymentMethodRepositoryTest {

    private final Logger            log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Transactional
    @Test
    public void testSavePaymentMethod() {
        PaymentMethod pm = new PaymentMethod();
        pm.setBrand("VISA");
        pm.setLast4("4242");
        pm.setName("John Doe");

        pm = paymentMethodRepository.saveAndFlush(pm);

        log.info(pm.toJson());

        assertNotNull(pm);
        assertNotEquals(0, pm.getId().longValue());
    }

}
