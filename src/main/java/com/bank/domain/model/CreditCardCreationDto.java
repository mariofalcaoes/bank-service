package com.bank.domain.model;


import com.bank.domain.CardType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CreditCardCreationDto {

    @NotEmpty
    @NotNull
    private String number;

    @NotEmpty
    @NotNull
    @Size(min = 5, max = 120)
    private String name;
    @NotEmpty
    @NotNull
    @Size(min = 3, max = 3)
    private String cvv;

    @NotNull
    @Min(value = 500)
    private BigDecimal limit;
    @NotNull
    @Enumerated(EnumType.STRING)
    private CardType type;

    @NotNull
    private Long accountId;

    @NotNull
    private LocalDateTime expirationDate;
}