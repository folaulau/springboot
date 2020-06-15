package com.folaukaveinga.testing.utility;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.folaukaveinga.testing.dto.PaymentMethodCreateDTO;
import com.folaukaveinga.testing.paymentmethod.PaymentMethod;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EntityDTOMapper {

    
    PaymentMethod mapPaymentMethodCreateDTOToPaymentMethod(PaymentMethodCreateDTO paymentMethodCreateDTO);
}
