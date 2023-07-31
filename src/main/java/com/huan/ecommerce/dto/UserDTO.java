package com.huan.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO  {
    private Long id;

    @Email(message = "Email không hợp lệ")
    private String email;

    @Length(min = 8)
    private String password;

    @NotBlank(message = "First name must not be blank")
    private String firstName;

    @NotBlank(message = "Last name must not be blank")
    private String lastName;

    private Character sex;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Date of birth field must not be null")
    private Date DOB;

    @Valid
    @NotNull(message = "Address field must not be blank")
    private AddressDTO address;

    @NotEmpty(message = "User must have at least one role")
    private List<Long> roleIdList = new ArrayList<>();
}