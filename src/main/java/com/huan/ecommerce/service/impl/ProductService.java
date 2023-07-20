package com.huan.ecommerce.service.impl;

import com.huan.ecommerce.dto.ProductDTO;
import com.huan.ecommerce.entity.Product;
import com.huan.ecommerce.mapper.EntityDTOMapper;
import com.huan.ecommerce.repository.BrandRepository;
import com.huan.ecommerce.repository.CategoryRepository;
import com.huan.ecommerce.repository.ProductRepository;
import com.huan.ecommerce.repository.SupplierRepository;
import com.huan.ecommerce.service.IProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

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
    public ProductDTO findProductById(int id) {
        return EntityDTOMapper.mapProductToDTO(productRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Product cannot be found: product ID " + id)));
    }

    /**
     * @return
     */
    @Override
    public Page<ProductDTO> findAll(Pageable pageable) {
        return productRepository.findAll(pageable).map(EntityDTOMapper::mapProductToDTO);
    }

    @Override
    public ProductDTO updateProductPrice(Long productId, double newPrice) {
        Product updatedProduct = productRepository.findById(productId.intValue()).orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + productId));
        updatedProduct.setPrice(newPrice);
        return EntityDTOMapper.mapProductToDTO(productRepository.save(updatedProduct));
    }

    @Override
    public Page<ProductDTO> findProductByBrandId(int brandId, Pageable pageable) {
        Page<ProductDTO> productCollection = productRepository.findProductsByBrandId(brandId,pageable).map(EntityDTOMapper::mapProductToDTO);
        if (productCollection.getTotalElements() == 0) {
            throw new EntityNotFoundException("Cannot find any product, maybe there is no such brand");
        }
        return productCollection;
    }

    /**
     * @param categoryId
     * @return
     */
    @Override
    public Page<ProductDTO> findProductByCategoryId(int categoryId, Pageable pageable) {
        Page<ProductDTO> productCollection = productRepository.findProductsByCategoryId(categoryId, pageable).map(EntityDTOMapper::mapProductToDTO);
        if (productCollection.getTotalElements() == 0) {
            throw new EntityNotFoundException("Cannot find any product, maybe there is no such category");
        }
        return productCollection;
    }

    /**
     * @param product
     * @return
     */
    @Override
    public ProductDTO saveProduct(ProductDTO product) {
        Product savedProduct = EntityDTOMapper.mapProductDTOToEntity(product);
        savedProduct.setCategory(categoryRepository.findById(product.getCategoryId()).orElseThrow(() -> new EntityNotFoundException("Category not found" + product.getCategoryId())));
        savedProduct.setBrand(brandRepository.findById(product.getBrandId()).orElseThrow(() -> new EntityNotFoundException("Brand not found" + product.getBrandId())));
        savedProduct.setSupplier(supplierRepository.findById(product.getSupplierId()).orElseThrow(() -> new EntityNotFoundException("Supplier not found" + product.getSupplierId())));
        return EntityDTOMapper.mapProductToDTO(productRepository.save(savedProduct));
    }
}
