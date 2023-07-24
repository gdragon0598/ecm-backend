package com.huan.ecommerce.service;

import com.huan.ecommerce.dto.ProductDTO;
import com.huan.ecommerce.dto.ProductDetailDTO;
import com.huan.ecommerce.entity.ProductDetail;

public interface IProductDetailService {
    ProductDetailDTO saveProduct(ProductDetailDTO productDetailDTO);

    ProductDetailDTO updateProduct(ProductDetailDTO productDetailDTO);

    ProductDetailDTO getProductDetailByProductId(Long productId);
}
