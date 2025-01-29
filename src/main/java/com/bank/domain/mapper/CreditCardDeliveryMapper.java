package com.bank.domain.mapper;

import com.bank.domain.entity.CreditCard;
import com.bank.domain.entity.CreditCardDelivery;
import com.bank.domain.model.CreditCardDeliveryDto;

public class CreditCardDeliveryMapper {
    public static CreditCardDelivery toCreditCardDelivery(CreditCardDeliveryDto creditCardDeliveryDto) {
        return CreditCardDelivery.builder()
                .creditCard(CreditCard.builder().id(creditCardDeliveryDto.getTrackingId()).build())
                .deliveryDate(creditCardDeliveryDto.getDeliveryDate())
                .deliveryAddress(creditCardDeliveryDto.getDeliveryAddress())
                .deliveryStatus(creditCardDeliveryDto.getStatus())
                .deliveryReturnReason(creditCardDeliveryDto.getReturnReason())
                .build();
    }

    public static CreditCardDeliveryDto toCreditCardDelivery(CreditCardDelivery creditCardDelivery) {
        return CreditCardDeliveryDto.builder()
                .trackingId(creditCardDelivery.getCreditCard().getId())
                .deliveryDate(creditCardDelivery.getDeliveryDate())
                .deliveryAddress(creditCardDelivery.getDeliveryAddress())
                .status(creditCardDelivery.getDeliveryStatus())
                .returnReason(creditCardDelivery.getDeliveryReturnReason())
                .build();
    }

}

