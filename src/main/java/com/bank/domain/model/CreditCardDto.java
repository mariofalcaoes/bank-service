package com.bank.domain.model;


import com.bank.domain.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CreditCardDto {
    private Long id;
    private String number;
    private String name;
    private String ccv;
    private CardType type;
}