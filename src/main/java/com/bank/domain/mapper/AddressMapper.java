package com.bank.domain.mapper;

import com.bank.domain.entity.Address;
import com.bank.domain.entity.Customer;
import com.bank.domain.model.AddressDto;


public class AddressMapper {
    public static Address toAddress(AddressDto addressDto, Long customerId) {
        return Address.builder()
                .zipCode(addressDto.getZipCode())
                .street(addressDto.getStreet())
                .district(addressDto.getDistrict())
                .customer(Customer.builder().id(customerId).build())
                .number(addressDto.getNumber())
                .build();
    }

    public static AddressDto toAddress(Address address) {
        return AddressDto.builder()
                .id(address.getId())
                .zipCode(address.getZipCode())
                .street(address.getStreet())
                .district(address.getDistrict())
                .number(address.getNumber())
                .build();
    }


}
