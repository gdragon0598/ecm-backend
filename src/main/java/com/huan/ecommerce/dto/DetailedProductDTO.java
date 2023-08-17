package com.huan.ecommerce.dto;

import lombok.Data;

import java.util.List;

@Data
public class DetailedProductDTO {
    private Long id;
    private String name;
    private Double price;
    private Long categoryId;
    private Long brandId;
    private String category;
    private String brand;
    private String imageUrl;
    private Integer sale;
    private Long supplierId;
    private Boolean isNew;
    private Integer numberSoldItems;
    private Integer total;
    private ProductDetailDTO productDetail;
    private List<CommentDTO> commentList;
}
