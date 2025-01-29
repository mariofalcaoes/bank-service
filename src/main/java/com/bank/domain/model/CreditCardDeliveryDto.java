package com.bank.domain.model;


import com.bank.domain.CardDeliveryStatus;
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
public class CreditCardDeliveryDto {
    @NotNull
    @JsonProperty("tracking_id")
    private Long trackingId;
    @NotNull
    @JsonProperty("delivery_status")
    private CardDeliveryStatus status;
    @NotNull
    @NotBlank
    @JsonProperty("delivery_return_reason")
    private String returnReason;
    @NotNull
    @NotBlank
    @JsonProperty("delivery_address")
    private String deliveryAddress;
    @NotNull
    @JsonProperty("delivery_date")
    private LocalDateTime deliveryDate;
}