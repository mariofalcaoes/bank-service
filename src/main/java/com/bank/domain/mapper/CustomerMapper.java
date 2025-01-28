package com.bank.domain.mapper;

import com.bank.domain.entity.Customer;
import com.bank.domain.model.AddressDto;
import com.bank.domain.model.CustomerCreationDto;
import com.bank.domain.model.CustomerDto;


public class CustomerMapper {

    public static Customer toCustomer(CustomerCreationDto customerDto) {
        return Customer.builder()
                .name(customerDto.getName())
                .cpf(customerDto.getCpf())
                .build();
    }

    public static CustomerDto toCustomer(Customer customer) {
        AddressDto address = AddressMapper.toAddress(customer.getAddress());
        return CustomerDto.builder()
                .name(customer.getName())
                .id(customer.getId())
                .cpf(customer.getCpf())
                .address(address)
                .build();
    }

}
