package com.huan.ecommerce.controller;

import com.huan.ecommerce.dto.ProductDetailDTO;
import com.huan.ecommerce.service.IProductDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product-detail")
public class ProductDetailController {
    @Autowired
    private IProductDetailService productDetailService;

    @PostMapping
    public String addProductDetail(@RequestBody @Valid ProductDetailDTO productDetailDTO) {
        productDetailService.saveProduct(productDetailDTO);
        return "Product detail added successfully";
    }

    @PutMapping
    public String updateProductDetail(@RequestBody @Valid ProductDetailDTO productDetailDTO) {
        productDetailService.updateProduct(productDetailDTO);
        return "Product detail updated successfully";
    }
}
