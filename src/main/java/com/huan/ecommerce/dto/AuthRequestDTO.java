package com.huan.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
@Data
public class AuthRequestDTO {
    @NotNull
    @Email
    private String email;

    @NotNull
    @Length(min = 6)
    private String password;

}
