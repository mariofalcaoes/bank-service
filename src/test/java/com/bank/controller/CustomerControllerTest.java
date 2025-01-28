//package com.order.controller;
//
//import com.order.domain.model.CustomerDto;
//import com.order.service.CustomerService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PagedResourcesAssembler;
//import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.PagedModel;
//
//import java.math.BigDecimal;
//import java.util.Collections;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class CustomerControllerTest {
//
//    @Mock
//    private CustomerService customerService;
//    @Mock
//    private PagedResourcesAssembler<CustomerDto> pagedResourcesAssembler;
//    @InjectMocks
//    private CustomerController customerController;
//
//    @Test
//    void findAll() {
//        Pageable pageable = Page.empty().getPageable();
//        when(customerService.findAll(any())).thenReturn(new PageImpl<>(Collections.singletonList(new CustomerDto()),
//                pageable, 1));
//        PagedModel<EntityModel<CustomerDto>> pageResult = Mockito.mock(PagedModel.class);
//        PagedModel.PageMetadata pageMetadata = Mockito.mock(PagedModel.PageMetadata.class);
//        Mockito.doReturn(1L).when(pageMetadata).getSize();
//        Mockito.doReturn(pageMetadata).when(pageResult).getMetadata();
//        Mockito.doReturn(pageResult).when(pagedResourcesAssembler)
//                .toModel(any(Page.class));
//        PagedModel<EntityModel<CustomerDto>> page = customerController.findAll(pageable);
//        assertEquals(1, page.getMetadata().getSize());
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
//
//        Mockito.doReturn(customerDto)
//                .when(this.customerService).create(any(CustomerDto.class));
//        CustomerDto result = this.customerController.create(customerDto);
//
//        assertNotNull(result);
//        Mockito.verify(this.customerService, times(1))
//                .create(any(CustomerDto.class));
//    }
//}