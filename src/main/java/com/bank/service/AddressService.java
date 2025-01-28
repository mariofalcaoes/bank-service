package com.bank.service;

import com.bank.domain.entity.Address;
import com.bank.domain.mapper.AddressMapper;
import com.bank.domain.model.AddressDto;
import com.bank.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public Address create(AddressDto addressDto, Long customerId) {
        log.info("Creating address with data {}", addressDto);
        Address address = this.addressRepository.save(AddressMapper.toAddress(addressDto, customerId));
        log.info("Address saved with success id {}", address.getId());
        return address;
    }

}
