package com.huan.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductDTO {
    @NotBlank(message = "name must not be blank")
    private String name;

    @NotBlank(message = "price must not be blank")
    private Double price;

    @NotBlank(message = "category id must not be blank")
    private Long categoryId;

    @NotBlank(message = "category id must not be blank")
    private Long brandId;

    @NotBlank(message = "image url must not be blank")
    private String imageUrl;

    private Integer sale;

    @NotBlank(message = "supplier id must not be blank")
    private Long supplierId;
    private Boolean isNew;
    private Integer numberSoldItems;
    private Integer total;

}
