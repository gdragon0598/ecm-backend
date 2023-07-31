package com.huan.ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class UserRoleUpdateDTO {
    @NotNull(message = "the role id list must not be null")
    @Size(min = 1, message = "user must have at least one role")
    List<Long> roleIdList;
}
