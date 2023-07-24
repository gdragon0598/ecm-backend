package com.huan.ecommerce.dto;

import com.huan.ecommerce.entity.Address;
import com.huan.ecommerce.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Date;
import java.util.Set;

@Data
public class UserDTO  {
    private Long id;

    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "First name must not be blank")
    private String firstName;

    @NotBlank(message = "Last name must not be blank")
    private String lastName;

    @NotBlank(message = "Sex field must not be blank")
    private Character sex;

    @NotBlank(message = "Date of birth field must not be blank")
    private Date DOB;

    @NotBlank(message = "Address field must not be blank")
    private AddressDTO address;
}