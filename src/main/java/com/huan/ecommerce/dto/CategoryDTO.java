package com.huan.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryDTO {
    private Long id;

    @NotBlank(message = "name of brand must not be blank")
    private String name;

    @NotNull(message = "description must not be null")
    private String description;

    private String iconUrl;
}
