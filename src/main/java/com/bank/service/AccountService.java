package com.bank.service;

import com.bank.domain.AccountStatus;
import com.bank.domain.entity.Account;
import com.bank.domain.mapper.AccountMapper;
import com.bank.domain.model.AccountCreationDto;
import com.bank.domain.model.AccountDto;
import com.bank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountDto create(AccountCreationDto accountDto) {
        log.info("Creating account with data {}", accountDto);
        Account accountToCreate = AccountMapper.toAccount(accountDto);
        accountToCreate.setStatus(AccountStatus.ENABLED);
        Account account = this.accountRepository.save(accountToCreate);
        log.info("Account saved with success id {}", account.getId());
        return AccountMapper.toAccount(account);
    }

    public void cancel(Long accountId) {
        log.info("Canceling account with id {}", accountId);
        this.accountRepository.updateStatus(accountId, AccountStatus.CANCELLED);
        log.info("Account canceled with success id {}", accountId);
    }

}
