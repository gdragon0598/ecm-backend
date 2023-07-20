package com.huan.ecommerce.controller;


import com.huan.ecommerce.dto.ProductDTO;
import com.huan.ecommerce.entity.Product;
import com.huan.ecommerce.mapper.ProductMapper;
import com.huan.ecommerce.service.IProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> getListOfProducts() {
        List<Product> products = productService.findAll();
        List<ProductDTO> productDTOList = products.stream().map(ProductMapper::mapProductToDTO).toList();
        return ResponseEntity.ok(productDTOList);
    }
    @PostMapping("")
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO) {
        Product savedProduct = productService.saveProduct(productDTO);
        return ResponseEntity.ok("Product added successfully");
    }
    @PutMapping("/{productId}/price")
    public ResponseEntity<String> updateProductPrice(@PathVariable Long productId, @RequestParam double newPrice) {
            Product updatedProduct = productService.updateProductPrice(productId, newPrice);
            return ResponseEntity.ok("Product price updated successfully. New price: " + updatedProduct.getPrice());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = productService.findProductById(id);
        return ResponseEntity.ok(product);
    }
    @GetMapping("/category")
    public ResponseEntity<Collection<Product>> getProductsByCategoryId(@RequestParam Integer id) {
        Collection<Product> products = productService.findProductByCategoryId(id);
        return ResponseEntity.ok(products);

    }
}


