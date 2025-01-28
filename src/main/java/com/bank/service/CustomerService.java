package com.bank.service;

import com.bank.domain.entity.Address;
import com.bank.domain.mapper.CustomerMapper;
import com.bank.domain.entity.Customer;
import com.bank.domain.model.AddressDto;
import com.bank.domain.model.CustomerCreationDto;
import com.bank.domain.model.CustomerDto;
import com.bank.exception.BusinessException;
import com.bank.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressService addressService;

    @Cacheable("customers")
    public Page<CustomerDto> findAll(Pageable pageable) {
        return this.customerRepository.findAllByOrderByIdDesc(pageable)
                .map(CustomerMapper::toCustomer);
    }

    public CustomerDto create(CustomerCreationDto customerDto) {
        log.info("Creating customer with data {}", customerDto);
        validate(customerDto);
        Customer customer = this.customerRepository.save(CustomerMapper.toCustomer(customerDto));
        Address address = this.addressService.create(customerDto.getAddress(), customer.getId());
        customer.setAddress(address);
        log.info("Customer saved with success id {} ", customer.getId());
        return CustomerMapper.toCustomer(customer);
    }


    private void validate(CustomerCreationDto customer) {
        Optional<Customer> customerExisting = this.customerRepository.findByCpf(customer.getCpf());
        if (customerExisting.isPresent()) {
            throw new BusinessException("Customer already exists",
                    HttpStatus.PRECONDITION_FAILED);
        }
    }

}
