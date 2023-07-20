package com.huan.ecommerce.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class AddressDTO {
    @NotBlank(message = "Street field must not be blank")
    private String street;
    @NotBlank(message = "City field must not be blank")
    private String city;
    @NotBlank(message = "Province field must not be blank")
    private String province;
    @NotBlank(message = "Postal code field must not be blank")
    private String postalCode;
}
