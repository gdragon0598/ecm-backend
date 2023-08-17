package com.huan.ecommerce.dto;

import lombok.Data;

@Data
public class SimpleResponseProductDTO {
    private Long id;
    private String name;
    private Double price;
    private String imageUrl;
    private Integer sale;
    private Boolean isNew;
    private Integer numberSoldItems;
    private Float rating;
}
