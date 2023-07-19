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
        try {
            Product savedProduct = productService.saveProduct(productDTO);
            return ResponseEntity.ok("Product added successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/{productId}/price")
    public ResponseEntity<String> updateProductPrice(@PathVariable Long productId, @RequestParam double newPrice) {
        try {
            Product updatedProduct = productService.updateProductPrice(productId, newPrice);
            return ResponseEntity.ok("Product price updated successfully. New price: " + updatedProduct.getPrice());
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        try{
            Product product = productService.findProductById(id);
            return ResponseEntity.ok(product);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/category")
    public ResponseEntity<Collection<Product>> getProductsByCategoryId(@RequestParam Integer id) {
        Collection<Product> products = productService.findProductByCategoryId(id);
        if (products.size() > 0)
            return ResponseEntity.ok(products);
        else return ResponseEntity.notFound().build();
    }
}


