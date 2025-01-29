package com.bank.controller;

import com.bank.domain.entity.CreditCardDelivery;
import com.bank.domain.model.CreditCardCreationDto;
import com.bank.domain.model.CreditCardDeliveryDto;
import com.bank.domain.model.CreditCardDto;
import com.bank.domain.model.CreditCardRemissionDto;
import com.bank.domain.model.CreditCardUpdateDto;
import com.bank.service.CreditCardDeliveryService;
import com.bank.service.CreditCardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class CreditCardDeliveryController {

    private final CreditCardDeliveryService creditCardService;

    @PostMapping(value = "/webhook/credit-card-delivery")
    @ResponseStatus(HttpStatus.OK)
    public CreditCardDeliveryDto create(@Valid @RequestBody CreditCardDeliveryDto creditCardCreationDto) {
        log.info("Delivery credit card");
        return this.creditCardService.create(creditCardCreationDto);
    }

}
