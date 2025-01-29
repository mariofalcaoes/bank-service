package com.bank.service;

import com.bank.domain.CardStatus;
import com.bank.domain.CardType;
import com.bank.domain.entity.Account;
import com.bank.domain.entity.CreditCard;
import com.bank.domain.model.CreditCardCreationDto;
import com.bank.domain.model.CreditCardDto;
import com.bank.domain.model.CreditCardRemissionDto;
import com.bank.domain.model.CreditCardUpdateDto;
import com.bank.exception.BusinessException;
import com.bank.repository.CreditCardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditCardServiceTest {
    @Mock
    private CreditCardRepository creditCardRepository;

    @InjectMocks
    private CreditCardService creditCardService;

    @Test
    void testCreateCreditCard() {
        // Arrange
        CreditCardCreationDto creationDto = new CreditCardCreationDto();
        // Populate creationDto with test data
        creationDto.setNumber("1234567812345678");
        creationDto.setName("John Doe");
        creationDto.setCvv("123");
        creationDto.setLimit(new BigDecimal("5000"));
        creationDto.setType(CardType.VIRTUAL);
        creationDto.setAccountId(1L);

        CreditCard creditCardToCreate = new CreditCard();
        creditCardToCreate.setId(1L);
        creditCardToCreate.setNumber("1234567812345678");
        creditCardToCreate.setStatus(CardStatus.BLOCKED);
        creditCardToCreate.setAccount(Account.builder().id(1L).build());

        CreditCardDto creditCardDto = new CreditCardDto();
        creditCardDto.setId(1L);

        when(creditCardRepository.save(any(CreditCard.class))).thenReturn(creditCardToCreate);

        // Act
        CreditCardDto createdCreditCard = creditCardService.create(creationDto);

        // Assert
        assertNotNull(createdCreditCard);
        assertEquals(1L, createdCreditCard.getId());
        verify(creditCardRepository, times(1)).save(any(CreditCard.class));
    }

    @Test
    void testRemissionCreditCardPhysical() {
        // Arrange
        CreditCardRemissionDto remissionDto = new CreditCardRemissionDto();
        remissionDto.setId(1L);
        remissionDto.setReason("Damaged");

        CreditCard creditCard = new CreditCard();
        creditCard.setId(1L);
        creditCard.setType(CardType.PHYSICAL);

        when(creditCardRepository.findById(1L)).thenReturn(Optional.of(creditCard));

        // Act
        creditCardService.remission(remissionDto);

        // Assert
        verify(creditCardRepository, times(1)).updateStatus(1L, "Damaged", CardStatus.BLOCKED);
    }

    @Test
    void testRemissionCreditCardNonPhysical() {
        // Arrange
        CreditCardRemissionDto remissionDto = new CreditCardRemissionDto();
        remissionDto.setId(1L);
        remissionDto.setReason("Damaged");

        CreditCard creditCard = new CreditCard();
        creditCard.setId(1L);
        creditCard.setType(CardType.VIRTUAL);

        when(creditCardRepository.findById(1L)).thenReturn(Optional.of(creditCard));

        // Act and Assert
        BusinessException exception = assertThrows(BusinessException.class, () ->
            creditCardService.remission(remissionDto)
        );
        assertEquals("Only physical credit card can be remission", exception.getMessage());
        assertEquals(HttpStatus.PRECONDITION_FAILED, exception.getHttpStatus());
    }

    @Test
    void testRemissionCreditCardNotFound() {
        // Arrange
        CreditCardRemissionDto remissionDto = new CreditCardRemissionDto();
        remissionDto.setId(1L);
        remissionDto.setReason("Damaged");

        when(creditCardRepository.findById(1L)).thenReturn(Optional.empty());

        // Act and Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            creditCardService.remission(remissionDto);
        });
        assertEquals("Card does not exists", exception.getMessage());
        assertEquals(HttpStatus.PRECONDITION_FAILED, exception.getHttpStatus());
    }

    @Test
    void testUpdateStatus() {
        // Arrange
        Long cardId = 1L;
        String reason = "Fraudulent transaction";

        // Act
        creditCardService.updateStatus(cardId, reason);

        // Assert
        verify(creditCardRepository, times(1)).updateStatus(cardId, reason, CardStatus.ENABLED);
    }

    @Test
    void testUpdateValidationInfo() {
        // Arrange
        CreditCardUpdateDto updateDto = new CreditCardUpdateDto();
        updateDto.setCardId(1L);
        updateDto.setNextCvv("321");
        updateDto.setExpirationDate(LocalDateTime.now().plusYears(1));

        // Act
        creditCardService.updateValidationInfo(updateDto);

        // Assert
        verify(creditCardRepository, times(1)).updateCard(1L, "321", updateDto.getExpirationDate());
    }
}