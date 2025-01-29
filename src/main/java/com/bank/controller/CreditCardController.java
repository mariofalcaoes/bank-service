package com.bank.controller;

import com.bank.domain.model.CreditCardCreationDto;
import com.bank.domain.model.CreditCardDto;
import com.bank.domain.model.CreditCardRemissionDto;
import com.bank.domain.model.CreditCardUpdateDto;
import com.bank.service.CreditCardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class CreditCardController {

    private final CreditCardService creditCardService;

    @PostMapping(value = "/credit-card")
    @ResponseStatus(HttpStatus.OK)
    public CreditCardDto create(@Valid @RequestBody CreditCardCreationDto creditCardCreationDto) {
        log.info("Creating credit card");
        return this.creditCardService.create(creditCardCreationDto);
    }

    @DeleteMapping(value = "/credit-card/remission")
    @ResponseStatus(HttpStatus.OK)
    public void remission(@Valid @RequestBody CreditCardRemissionDto creditCardRemissionDto) {
        log.info("Canceling credit card");
        this.creditCardService.remission(creditCardRemissionDto);
    }

    @PatchMapping(value = "/credit-card/cvv")
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody CreditCardUpdateDto creditCardUpdateDto) {
        log.info("Update credit card");
        this.creditCardService.updateValidationInfo(creditCardUpdateDto);
    }

}
