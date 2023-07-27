package com.huan.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

@Data
public class OrderDetailDTO {
    @NotNull(message = "price must not be null")
    private Integer price;

    @Min(value = 1, message = "quantity must be greater than 0")
    private Integer quantity;

    @NotNull(message = "productId must not be null")
    private Long productId;
}
