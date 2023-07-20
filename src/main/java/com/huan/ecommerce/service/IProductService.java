package com.huan.ecommerce.service;

import com.huan.ecommerce.dto.ProductDTO;
import com.huan.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface IProductService {
    public ProductDTO findProductById(int id);
    public Page<ProductDTO> findProductByCategoryId(int categoryId, org.springframework.data.domain.Pageable pageable);
    public ProductDTO saveProduct(ProductDTO product);
    public Page<ProductDTO> findAll(Pageable pageable);
    public ProductDTO updateProductPrice(Long productId, double newPrice);
    public Page<ProductDTO> findProductByBrandId(int brandId, Pageable pageable);
}
