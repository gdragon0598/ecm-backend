package com.huan.ecommerce.service.impl;

import com.huan.ecommerce.dto.AddProductDTO;
import com.huan.ecommerce.dto.DetailedProductDTO;
import com.huan.ecommerce.dto.SimpleResponseProductDTO;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class    ProductService implements IProductService {
    @Autowired
    private  EntityDTOMapper entityDTOMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    @Transactional(readOnly = true)
    public DetailedProductDTO findProductById(int id) {
        return entityDTOMapper.mapProductToDetailedDTO(productRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Product cannot be found: product ID " + id)));
    }

    /**
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<SimpleResponseProductDTO> findAll(Pageable pageable) {
        return productRepository.findAll(pageable).map(entityDTOMapper::mapProductToSimpleResponseDTO).toList();
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public AddProductDTO updateProductPrice(Long productId, double newPrice) {
        Product updatedProduct = productRepository.findById(productId.intValue()).orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + productId));
        updatedProduct.setPrice(newPrice);
        return entityDTOMapper.mapProductToDTO(productRepository.save(updatedProduct));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SimpleResponseProductDTO> findProductByBrandId(Long brandId, Pageable pageable) {
        Page<SimpleResponseProductDTO> productCollection = productRepository.findProductsByBrandId(brandId.intValue(),pageable).map(entityDTOMapper::mapProductToSimpleResponseDTO);
        if (productCollection.getTotalElements() == 0) {
            throw new EntityNotFoundException("Cannot find any product, maybe there is no such brand");
        }
        return productCollection;
    }

    /**
     * @param id
     * @param addProductDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public AddProductDTO updateProduct(Integer id, AddProductDTO addProductDTO) {
        Product updatedProduct = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + id));
        updatedProduct.setPrice(addProductDTO.getPrice());
        updatedProduct.setName(addProductDTO.getName());
        updatedProduct.setSale(addProductDTO.getSale());
        updatedProduct.setIsNew(addProductDTO.getIsNew());
        updatedProduct.setNumberSoldItems(addProductDTO.getNumberSoldItems());
        updatedProduct.setTotal(addProductDTO.getTotal());
        updatedProduct.setImageUrl(addProductDTO.getImageUrl());
        return entityDTOMapper.mapProductToDTO(productRepository.save(updatedProduct));
    }

    /**
     * @param categoryId
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SimpleResponseProductDTO> findProductByCategoryId(Long categoryId, Pageable pageable) {
        Page<SimpleResponseProductDTO> productCollection = productRepository.findProductsByCategoryId(categoryId.intValue(), pageable).map(entityDTOMapper::mapProductToSimpleResponseDTO);
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
    @Transactional(rollbackFor = Throwable.class)
    public AddProductDTO saveProduct(AddProductDTO product) {
        Product savedProduct = entityDTOMapper.mapProductDTOToEntity(product);
        savedProduct.setCategory(categoryRepository.findById(product.getCategoryId()).orElseThrow(() -> new EntityNotFoundException("Category not found" + product.getCategoryId())));
        savedProduct.setBrand(brandRepository.findById(product.getBrandId()).orElseThrow(() -> new EntityNotFoundException("Brand not found" + product.getBrandId())));
        savedProduct.setSupplier(supplierRepository.findById(product.getSupplierId()).orElseThrow(() -> new EntityNotFoundException("Supplier not found" + product.getSupplierId())));
        savedProduct = productRepository.save(savedProduct);
        return entityDTOMapper.mapProductToDTO(savedProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SimpleResponseProductDTO> findTopProductsBySale(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "sale"));
        Page<SimpleResponseProductDTO> productDTOPage = productRepository.findAll(pageable).map(entityDTOMapper::mapProductToSimpleResponseDTO);
        return productDTOPage;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SimpleResponseProductDTO> findPageOfProductsIsNew(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "id"));
        List<SimpleResponseProductDTO> productDTOPage = productRepository.findProductsByIsNew(pageable).map(entityDTOMapper::mapProductToSimpleResponseDTO).toList()   ;
        return productDTOPage;
    }

    /**
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void deleteProductById(Long id) {
        productRepository.deleteById(id.intValue());
    }
}

