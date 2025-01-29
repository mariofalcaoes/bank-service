package com.bank.service;

import com.bank.domain.AccountStatus;
import com.bank.domain.AccountType;
import com.bank.domain.entity.Account;
import com.bank.domain.entity.Customer;
import com.bank.domain.model.AccountCreationDto;
import com.bank.domain.model.AccountDto;
import com.bank.repository.AccountRepository;
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
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    void testCreateAccount() {
        // Arrange
        AccountCreationDto accountCreationDto = new AccountCreationDto();
        // Set required properties for accountCreationDto
        accountCreationDto.setNumber("6541236");
        accountCreationDto.setAgency("123");
        accountCreationDto.setCustomerId(1L);
        accountCreationDto.setType(AccountType.CORRENTE);

        Account accountToCreate = new Account();
        accountToCreate.setId(1L);
        accountToCreate.setNumber("6541236");
        accountToCreate.setStatus(AccountStatus.ENABLED);
        accountToCreate.setAgency("123");
        accountToCreate.setType(AccountType.CORRENTE);
        accountToCreate.setCustomer(Customer.builder().id(1L).build());

        // Mock the behavior of AccountRepository
        when(accountRepository.save(any(Account.class))).thenReturn(accountToCreate);

        // Act
        AccountDto createdAccount = accountService.create(accountCreationDto);

        // Assert
        assertNotNull(createdAccount);
        assertEquals(1L, createdAccount.getId());
        assertEquals(AccountType.CORRENTE, createdAccount.getType());
        assertEquals(AccountStatus.ENABLED, createdAccount.getStatus());
        assertEquals("6541236", createdAccount.getNumber());

        // Verify interactions
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    void testCancelAccount() {
        // Arrange
        Long accountId = 1L;

        // Act
        accountService.cancel(accountId);

        // Assert
        verify(accountRepository, times(1)).updateStatus(accountId, AccountStatus.CANCELLED);
    }
}