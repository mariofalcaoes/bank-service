package com.bank.domain.mapper;

import com.bank.domain.entity.Account;
import com.bank.domain.entity.CreditCard;
import com.bank.domain.model.CreditCardCreationDto;
import com.bank.domain.model.CreditCardDto;

public class CreditCardMapper {
    public static CreditCard toCreditCard(CreditCardCreationDto creditCardCreationDto) {
        return CreditCard.builder()
                .number(creditCardCreationDto.getNumber())
                .cvv(creditCardCreationDto.getCvv())
                .name(creditCardCreationDto.getName())
                .creditLimit(creditCardCreationDto.getLimit())
                .expirationDate(creditCardCreationDto.getExpirationDate())
                .type(creditCardCreationDto.getType())
                .account(Account.builder().id(creditCardCreationDto.getAccountId()).build())
                .build();
    }

    public static CreditCardDto toCreditCard(CreditCard creditCard) {
        return CreditCardDto.builder()
                .number(creditCard.getNumber())
                .cvv(creditCard.getCvv())
                .name(creditCard.getName())
                .expirationDate(creditCard.getExpirationDate())
                .type(creditCard.getType())
                .limit(creditCard.getCreditLimit())
                .accountId(creditCard.getAccount().getId())
                .build();
    }

}

