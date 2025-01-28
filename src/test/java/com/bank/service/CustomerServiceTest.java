//package com.order.service;
//
//import com.order.domain.entity.Customer;
//import com.order.domain.entity.Status;
//import com.order.domain.model.CustomerDto;
//import com.order.exception.BusinessException;
//import com.order.repository.CustomerRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//
//import java.math.BigDecimal;
//import java.util.Collections;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class CustomerServiceTest {
//
//    @Mock
//    private CustomerRepository customerRepository;
//
//    @InjectMocks
//    private CustomerService customerService;
//
//    @Test
//    void findAll() {
//        Pageable pageable = Page.empty().getPageable();
//        when(customerRepository.findAllByOrderByIdDesc(any()))
//                .thenReturn(new PageImpl<>(Collections.singletonList(new Customer()),
//                        pageable, 1));
//        Page<CustomerDto> allRecords = customerService.findAll(pageable);
//        assertNotNull(allRecords);
//        assertEquals(1, allRecords.getTotalPages());
//        assertEquals(1, allRecords.getTotalElements());
//    }
//
//    @Test
//    void create() {
//        CustomerDto customerDto = CustomerDto.builder()
//                .code("123321")
//                .items(Collections.singletonList(ItemDto.builder()
//                        .quantity(2)
//                        .value(BigDecimal.ONE)
//                        .name("coca-cola")
//                        .build()))
//                .build();
//        Customer customer = Customer.builder()
//                .code(customerDto.getCode())
//                .status(Status.AUTHORIZED)
//                .valueTotal(BigDecimal.ONE)
//                .items(Collections.singletonList(Item.builder()
//                        .quantity(2)
//                        .value(BigDecimal.ONE)
//                        .name("coca-cola")
//                        .build()))
//                .build();
//        Mockito.doReturn(customer).when(this.customerRepository).save(any(Customer.class));
//        CustomerDto result = this.customerService.create(customerDto);
//        assertNotNull(result);
//        assertEquals(Status.AUTHORIZED, result.getStatus());
//        assertEquals(BigDecimal.ONE, result.getValueTotal());
//    }
//
//    @Test
//    void createWithDuplication() {
//        CustomerDto customerDto = CustomerDto.builder()
//                .code("123321")
//                .items(Collections.singletonList(ItemDto.builder()
//                        .quantity(2)
//                        .value(BigDecimal.ONE)
//                        .name("coca-cola")
//                        .build()))
//                .build();
//        Mockito.doReturn(Optional.of(new Customer())).when(customerRepository).findByCode(anyString());
//        assertThrows(BusinessException.class, () -> this.customerService.create(customerDto));
//    }
//}