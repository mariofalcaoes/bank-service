package com.bank.service;

import com.bank.domain.CardDeliveryStatus;
import com.bank.domain.entity.CreditCard;
import com.bank.domain.entity.CreditCardDelivery;
import com.bank.domain.model.CreditCardDeliveryDto;
import com.bank.repository.CreditCardDeliveryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditCardDeliveryServiceTest {

    @Mock
    private CreditCardDeliveryRepository creditCardDeliveryRepository;

    @Mock
    private CreditCardService creditCardService;

    @InjectMocks
    private CreditCardDeliveryService creditCardDeliveryService;

    @Test
    void testCreateCreditCardDelivery() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        CreditCardDeliveryDto creditCardDeliveryDto = new CreditCardDeliveryDto();
        creditCardDeliveryDto.setTrackingId(1L);
        creditCardDeliveryDto.setReturnReason("cliente nao encontrado");
        creditCardDeliveryDto.setStatus(CardDeliveryStatus.FAILED);
        creditCardDeliveryDto.setDeliveryAddress("rua da cohab numero 21 bairro cohab");
        creditCardDeliveryDto.setDeliveryDate(now);
        // Set other properties as required

        CreditCardDelivery creditCardDeliveryToSave = new CreditCardDelivery();
        creditCardDeliveryToSave.setId(1L);
        creditCardDeliveryToSave.setCreditCard(CreditCard.builder().id(1L).build());
        creditCardDeliveryToSave.setDeliveryReturnReason("cliente nao encontrado");
        creditCardDeliveryToSave.setDeliveryStatus(CardDeliveryStatus.FAILED);
        creditCardDeliveryToSave.setDeliveryAddress("rua da cohab numero 21 bairro cohab");
        creditCardDeliveryToSave.setDeliveryDate(now);

        // Mock the behavior of CreditCardDeliveryRepository and CreditCardService
        when(creditCardDeliveryRepository.save(any(CreditCardDelivery.class))).thenReturn(creditCardDeliveryToSave);

        // Act
        CreditCardDeliveryDto createdDelivery = creditCardDeliveryService.create(creditCardDeliveryDto);

        // Assert
        assertNotNull(createdDelivery);
        assertEquals(1L, createdDelivery.getTrackingId());
        assertEquals("cliente nao encontrado", createdDelivery.getReturnReason());

        // Verify interactions
        verify(creditCardDeliveryRepository, times(1)).save(any(CreditCardDelivery.class));
        verify(creditCardService, times(1)).updateStatus(1L, "cliente nao encontrado");
    }
}