package com.bank.domain.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDto {
    private Long id;
    @NotBlank
    @Pattern(regexp = "^\\d{8}$", message = "Zip code must be in the format 65064589")
    private String zipCode;
    @NotBlank
    @NotNull
    private String street;
    @NotBlank
    @NotNull
    private String district;
    private Integer number;
}