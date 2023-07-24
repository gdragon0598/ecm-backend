package com.huan.ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDetailDTO {
    private Long id;

    @NotNull(message = "Product Id must not be null")
    private Long productId;

    @NotNull(message = "description must not be null")
    private String description;

    @NotNull(message = "additional information must not be null")
    private String additionalInformation;


}
