package com.bank.service;

import com.bank.domain.entity.Account;
import com.bank.domain.entity.Address;
import com.bank.domain.mapper.AccountMapper;
import com.bank.domain.mapper.AddressMapper;
import com.bank.domain.model.AccountCreationDto;
import com.bank.domain.model.AccountDto;
import com.bank.domain.model.AddressDto;
import com.bank.repository.AccountRepository;
import com.bank.repository.AddressRepository;
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
        Account account = this.accountRepository.save(AccountMapper.toAccount(accountDto));
        log.info("Account saved with success id {}", account.getId());
        return AccountMapper.toAccount(account);
    }

}
