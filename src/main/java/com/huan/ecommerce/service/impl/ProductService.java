package com.huan.ecommerce.service.impl;

import com.huan.ecommerce.dto.ProductDTO;
import com.huan.ecommerce.entity.Product;
import com.huan.ecommerce.mapper.ProductMapper;
import com.huan.ecommerce.repository.BrandRepository;
import com.huan.ecommerce.repository.CategoryRepository;
import com.huan.ecommerce.repository.ProductRepository;
import com.huan.ecommerce.repository.SupplierRepository;
import com.huan.ecommerce.service.IProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public Product findProductById(int id) {
        return productRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Product cannot be found: product ID " + id));
    }

    /**
     * @return
     */
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProductPrice(Long productId, double newPrice) {
        Product updatedProduct = productRepository.findById(productId.intValue()).orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + productId));
        updatedProduct.setPrice(newPrice);
        return productRepository.save(updatedProduct);
    }

    /**
     * @param categoryId
     * @return
     */
    @Override
    public Collection<Product> findProductByCategoryId(int categoryId) {
        Collection<Product> productCollection = productRepository.findProductsByCategoryId(categoryId);
        if (productCollection.size() == 0) {
            throw new EntityNotFoundException("Cannot find any product, maybe there is no such category");
        }
        return productCollection;
    }

    /**
     * @param product
     * @return
     */
    @Override
    public Product saveProduct(ProductDTO product) {
        Product savedProduct = ProductMapper.mapProductDTOToEntity(product);
        savedProduct.setCategory(categoryRepository.findById(product.getCategoryId()).orElseThrow(() -> new EntityNotFoundException("Category not found" + product.getCategoryId())));
        savedProduct.setBrand(brandRepository.findById(product.getBrandId()).orElseThrow(() -> new EntityNotFoundException("Brand not found" + product.getBrandId())));
        savedProduct.setSupplier(supplierRepository.findById(product.getSupplierId()).orElseThrow(() -> new EntityNotFoundException("Supplier not found" + product.getSupplierId())));
        return productRepository.save(savedProduct);
    }
}
