package com.huan.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductImageDTO {
    @NotBlank(message = "image url must not be blank")
    private String imageUrl;
}
