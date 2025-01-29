package com.bank.domain.model;


import com.bank.domain.AccountStatus;
import com.bank.domain.AccountType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {
    private Long id;
    private String number;
    private String agency;
    private AccountType type;
    private AccountStatus status;
    private List<CreditCardDto> cards;
}