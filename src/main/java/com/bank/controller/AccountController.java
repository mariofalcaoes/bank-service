package com.bank.controller;

import com.bank.domain.model.AccountCreationDto;
import com.bank.domain.model.AccountDto;
import com.bank.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping(value = "/account")
    @ResponseStatus(HttpStatus.OK)
    public AccountDto create(@Valid @RequestBody AccountCreationDto accountCreationDto) {
        log.info("Creating account");
        return this.accountService.create(accountCreationDto);
    }

}
