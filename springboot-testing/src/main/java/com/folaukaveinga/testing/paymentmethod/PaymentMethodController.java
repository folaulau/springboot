package com.folaukaveinga.testing.paymentmethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.folaukaveinga.testing.dto.PaymentMethodCreateDTO;
import com.folaukaveinga.testing.user.User;
import com.folaukaveinga.testing.utility.EntityDTOMapper;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(value = "paymentmethods", produces = "Rest API for PaymentMethod operations", tags = "PaymentMethod Controller")
@RestController
@RequestMapping("/paymentmethods")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private EntityDTOMapper      entityDTOMapper;

    @PostMapping
    public ResponseEntity<PaymentMethod> addPaymentMethod(@RequestParam("userId") long userId, @RequestBody PaymentMethodCreateDTO paymentMethodCreateDTO) {
        log.info("addPaymentMethod: {}", paymentMethodCreateDTO.toString());
        PaymentMethod paymentMethod = entityDTOMapper.mapPaymentMethodCreateDTOToPaymentMethod(paymentMethodCreateDTO);
        paymentMethod = paymentMethodService.add(paymentMethod);
        return new ResponseEntity<>(paymentMethod, HttpStatus.OK);
    }

}
