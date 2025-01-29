package com.bank.service;

import com.bank.domain.entity.Address;
import com.bank.domain.model.AddressDto;
import com.bank.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    @Test
    void testCreateAddress() {
        // Arrange
        AddressDto addressDto = new AddressDto();
        addressDto.setStreet("123 Main St");
        addressDto.setDistrict("Cohaba");
        addressDto.setZipCode("35652546");
        addressDto.setNumber(23);

        Long customerId = 1L;

        Address addressToCreate = new Address();
        addressToCreate.setId(1L);
        addressToCreate.setStreet("123 Main St");
        addressToCreate.setDistrict("Cohaba");
        addressToCreate.setZipCode("35652546");
        addressToCreate.setNumber(23);

        // Mock the behavior of AddressRepository
        when(addressRepository.save(any(Address.class))).thenReturn(addressToCreate);

        // Act
        Address createdAddress = addressService.create(addressDto, customerId);

        // Assert
        assertNotNull(createdAddress);
        assertEquals(1L, createdAddress.getId());
        assertEquals("123 Main St", createdAddress.getStreet());
        assertEquals("35652546", createdAddress.getZipCode());

        // Verify interactions
        verify(addressRepository, times(1)).save(any(Address.class));
    }
}