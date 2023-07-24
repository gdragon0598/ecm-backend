package com.huan.ecommerce.service;

import com.huan.ecommerce.dto.ProductImageDTO;

import java.util.List;

public interface IProductImageService {
    public ProductImageDTO saveProductImage(ProductImageDTO productImageDTO);

    List<ProductImageDTO> getProductImagesByProductId(Long productId);
}
