package com.huan.ecommerce.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDTO {
    private Long id;

    @NotBlank(message = "name must not be blank")
    private String name;

    @NotNull(message = "price must not be blank")
    private Double price;

    @NotNull(message = "category id must not be blank")
    private Long categoryId;

    @NotNull(message = "category id must not be blank")
    private Long brandId;

    @NotBlank(message = "image url must not be blank")
    private String imageUrl;

    @Min(value = 0, message = "sale value must be >= 0 and <= 100")
    @Max(value = 100, message = "sale value must be <= 100")
    private Integer sale;

    @NotNull(message = "supplier id must not be blank")
    private Long supplierId;


    private Boolean isNew;
    private Integer numberSoldItems;
    private Integer total;

}
