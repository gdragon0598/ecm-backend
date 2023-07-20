package com.huan.ecommerce.service.impl;

import com.huan.ecommerce.entity.Product;
import com.huan.ecommerce.repository.ProductRepository;
import com.huan.ecommerce.service.IProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    public Product findProductById(int id) {
        return productRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
    }
}
