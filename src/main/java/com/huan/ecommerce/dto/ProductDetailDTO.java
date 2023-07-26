package com.huan.ecommerce.dto;

import com.huan.ecommerce.entity.ProductImage;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ProductDetailDTO {
    @Valid
    @NotEmpty(message = "Must have at lease one product image")
    private List<ProductImageDTO> productImage;

    @NotNull(message = "description must not be null")
    private String description;

    @NotNull(message = "additional information must not be null")
    private String additionalInformation;
}
