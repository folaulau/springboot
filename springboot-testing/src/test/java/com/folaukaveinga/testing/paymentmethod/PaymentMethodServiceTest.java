package com.folaukaveinga.testing.paymentmethod;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.folaukaveinga.testing.user.UserNtcService;

@RunWith(MockitoJUnitRunner.class)
public class PaymentMethodServiceTest {

    private final Logger            log = LoggerFactory.getLogger(this.getClass());

    @InjectMocks
    private PaymentMethodService    paymentMethodService = new PaymentMethodServiceImp();

    @Mock
    private PaymentMethodRepository paymentMethodRepository;

    @Mock
    private UserNtcService          userNtcService;

    /**
     * Use verify
     */
    @Test
    public void testAddPaymentMethod_Ok() {
        log.info("testAddPaymentMethod_Ok()");
        PaymentMethod pm = new PaymentMethod();
        pm.setBrand("VISA");
        pm.setLast4("4242");
        pm.setName("John Doe");

        when(paymentMethodRepository.saveAndFlush(any(PaymentMethod.class))).thenReturn(pm);

        when(userNtcService.notifyUserOfNewPaymentMethod(any(PaymentMethod.class))).thenReturn(true);

        paymentMethodService.add(pm);

        verify(userNtcService).notifyUserOfNewPaymentMethod(pm);
        log.info("testAddPaymentMethod_Ok done!");
    }

    @Test
    public void testAddPaymentMethod_Fail() {
        log.info("testAddPaymentMethod_Fail()");
        PaymentMethod pm = new PaymentMethod();
        pm.setBrand("VISA");
        pm.setLast4("4242");
        pm.setName("John Doe");

        when(paymentMethodRepository.saveAndFlush(any(PaymentMethod.class))).thenReturn(pm);

        when(userNtcService.notifyUserOfNewPaymentMethod(any(PaymentMethod.class))).thenReturn(true);

        paymentMethodService.add(pm);

        verify(userNtcService).notifyUserOfNewPaymentMethod(new PaymentMethod());
        log.info("testAddPaymentMethod_Fail done!");
    }

}
