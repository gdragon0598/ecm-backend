package com.huan.ecommerce.service;

import com.huan.ecommerce.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IProductService {
    ProductDTO findProductById(int id);
    Page<ProductDTO> findProductByCategoryId(Long categoryId, Pageable pageable);
    ProductDTO saveProduct(ProductDTO product);
    Page<ProductDTO> findAll(Pageable pageable);
    ProductDTO updateProductPrice(Long productId, double newPrice);
    Page<ProductDTO> findProductByBrandId(Long brandId, Pageable pageable);
    ProductDTO updateProduct(Integer id, ProductDTO productDTO);
    Page<ProductDTO> findTopProductsBySale(Pageable pageable);
    Page<ProductDTO> findPageOfProductsIsNew(Pageable pageable);
    void deleteProductById(Long id);

}
