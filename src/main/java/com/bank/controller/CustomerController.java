package com.bank.controller;

import com.bank.domain.model.CustomerCreationDto;
import com.bank.domain.model.CustomerDto;
import com.bank.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final PagedResourcesAssembler<CustomerDto> pagedResourcesAssembler;

    @GetMapping(value = "/customers")
    @ResponseStatus(HttpStatus.OK)
    public PagedModel<EntityModel<CustomerDto>> findAll(Pageable pageable) {
        log.info("Fetching all customers");
        return pagedResourcesAssembler.toModel(customerService.findAll(pageable));
    }

    @PostMapping(value = "/customer")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto create(@Valid @RequestBody CustomerCreationDto customerDto) {
        log.info("Creating customer");
        return this.customerService.create(customerDto);
    }

}
