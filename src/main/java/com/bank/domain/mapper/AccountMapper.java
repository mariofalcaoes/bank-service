package com.bank.domain.mapper;

import com.bank.domain.entity.Account;
import com.bank.domain.entity.Customer;
import com.bank.domain.model.AccountCreationDto;
import com.bank.domain.model.AccountDto;


public class AccountMapper {
    public static Account toAccount(AccountCreationDto accountDto) {
        return Account.builder()
                .number(accountDto.getNumber())
                .agency(accountDto.getAgency())
                .type(accountDto.getType())
                .customer(Customer.builder().id(accountDto.getCustomerId()).build())
                .build();
    }

    public static AccountDto toAccount(Account account) {
        return AccountDto.builder()
                .number(account.getNumber())
                .agency(account.getAgency())
                .status(account.getStatus())
                .type(account.getType())
                .id(account.getId())
                .build();
    }

}
