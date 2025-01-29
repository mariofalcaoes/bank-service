package com.bank.service;

import com.bank.domain.entity.Address;
import com.bank.domain.entity.Customer;
import com.bank.domain.model.AddressDto;
import com.bank.domain.model.CustomerCreationDto;
import com.bank.domain.model.CustomerDto;
import com.bank.exception.BusinessException;
import com.bank.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AddressService addressService;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void testCreateCustomerWhenCustomerDoesNotExist() {
        // Arrange
        CustomerCreationDto customerCreationDto = new CustomerCreationDto();
        customerCreationDto.setCpf("12345678901");
        AddressDto addressDto = new AddressDto();
        customerCreationDto.setAddress(addressDto);

        Customer customerToCreate = new Customer();
        customerToCreate.setId(1L);
        Address address = new Address();
        address.setId(1L);

        when(customerRepository.findByCpf(customerCreationDto.getCpf())).thenReturn(Optional.empty());
        when(customerRepository.save(any(Customer.class))).thenReturn(customerToCreate);
        when(addressService.create(any(AddressDto.class), anyLong())).thenReturn(address);

        // Act
        CustomerDto createdCustomer = customerService.create(customerCreationDto);

        // Assert
        assertNotNull(createdCustomer);
        assertEquals(1L, createdCustomer.getId());
        verify(customerRepository, times(1)).findByCpf(anyString());
        verify(customerRepository, times(1)).save(any(Customer.class));
        verify(addressService, times(1)).create(any(AddressDto.class), anyLong());
    }

    @Test
    void testCreateCustomerWhenCustomerExists() {
        // Arrange
        CustomerCreationDto customerCreationDto = new CustomerCreationDto();
        customerCreationDto.setCpf("12345678901");
        AddressDto addressDto = new AddressDto();
        customerCreationDto.setAddress(addressDto);

        Customer existingCustomer = new Customer();
        existingCustomer.setCpf("12345678901");

        when(customerRepository.findByCpf(customerCreationDto.getCpf())).thenReturn(Optional.of(existingCustomer));

        // Act and Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            customerService.create(customerCreationDto);
        });

        assertEquals("Customer already exists", exception.getMessage());
        assertEquals(HttpStatus.PRECONDITION_FAILED, exception.getHttpStatus());
    }
}
