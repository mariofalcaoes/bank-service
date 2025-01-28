package com.bank.domain.model;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class CustomerCreationDto {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String cpf;
    @Valid
    @NotNull
    private AddressDto address;
}