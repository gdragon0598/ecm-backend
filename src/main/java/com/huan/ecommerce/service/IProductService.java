package com.huan.ecommerce.service;

import com.huan.ecommerce.dto.ProductDTO;
import com.huan.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface IProductService {
    public Product findProductById(int id);
    public Page<Product> findProductByCategoryId(int categoryId, org.springframework.data.domain.Pageable pageable);
    public Product saveProduct(ProductDTO product);
    public Page<Product> findAll(Pageable pageable);
    public Product updateProductPrice(Long productId, double newPrice);
    public Page<Product> findProductByBrandId(int brandId, Pageable pageable);
}
