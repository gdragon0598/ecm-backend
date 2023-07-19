package com.huan.ecommerce.service;

import com.huan.ecommerce.dto.ProductDTO;
import com.huan.ecommerce.entity.Product;

import java.util.Collection;
import java.util.List;

public interface IProductService {
    public Product findProductById(int id);
    public Collection<Product> findProductByCategoryId(int categoryId);
    public Product saveProduct(ProductDTO product);
    public List<Product> findAll();

    public Product updateProductPrice(Long productId, double newPrice);
}
