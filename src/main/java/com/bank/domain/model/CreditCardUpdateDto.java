package com.bank.domain.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CreditCardUpdateDto {
    @NotNull
    @JsonProperty("account_id")
    private Long accountId;
    @NotNull
    @JsonProperty("card_id")
    private Long cardId;
    @NotNull
    @NotBlank
    @JsonProperty("next_cvv")
    private String nextCvv;
    @NotNull
    @JsonProperty("expiration_date")
    private LocalDateTime expirationDate;
}