package com.huan.ecommerce.service;

import com.huan.ecommerce.dto.AddProductDTO;
import com.huan.ecommerce.dto.DetailedProductDTO;
import com.huan.ecommerce.dto.SimpleResponseProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IProductService {
    DetailedProductDTO findProductById(int id);
    Page<SimpleResponseProductDTO> findProductByCategoryId(Long categoryId, Pageable pageable);
    AddProductDTO saveProduct(AddProductDTO product);
    List<SimpleResponseProductDTO> findAll(Pageable pageable);
    AddProductDTO updateProductPrice(Long productId, double newPrice);
    Page<SimpleResponseProductDTO> findProductByBrandId(Long brandId, Pageable pageable);
    AddProductDTO updateProduct(Integer id, AddProductDTO addProductDTO);
    Page<SimpleResponseProductDTO> findTopProductsBySale(Pageable pageable);
    List<SimpleResponseProductDTO> findPageOfProductsIsNew(Pageable pageable);
    void deleteProductById(Long id);

}
