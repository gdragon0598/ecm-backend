package com.huan.ecommerce.controller;

import com.huan.ecommerce.dto.ProductDTO;
import com.huan.ecommerce.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("")
    public Page<ProductDTO> getPageOfProducts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDTO> productDTOPage = productService.findAll(pageable);
        return productDTOPage;
    }
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional(rollbackFor = Throwable.class)
    public ResponseEntity<ProductDTO> addProduct(@RequestBody @Valid ProductDTO productDTO) {
        ProductDTO savedProduct = productService.saveProduct(productDTO);
        return ResponseEntity.ok(savedProduct);
    }
    @Transactional(rollbackFor = Throwable.class)
    @PutMapping("/{productId}/price")
    public ResponseEntity<ProductDTO> updateProductPrice(@PathVariable Long productId, @RequestParam double newPrice) {
        ProductDTO updatedProductDTO = productService.updateProductPrice(productId, newPrice);
        return  ResponseEntity.ok(updatedProductDTO);
    }
    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Integer id) {
        ProductDTO productDTO = productService.findProductById(id);
        return productDTO;
        //DETAILS
    }
    @GetMapping("/category/{categoryId}")
    public Page<ProductDTO> getPageProductsByCategoryId( @PathVariable Long categoryId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<ProductDTO> productDTOPage = productService.findProductByCategoryId(categoryId,pageable);
        return productDTOPage;
    }
    @GetMapping("/brand/{brandId}")
    public Page<ProductDTO> getProductsByBrandId(
            @PathVariable Long brandId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<ProductDTO> productDTOPage = productService.findProductByBrandId(brandId, pageable);
        return productDTOPage;
    }

    @PutMapping("")
    @Transactional(rollbackFor = Throwable.class)
    public  ResponseEntity<ProductDTO> updateProductById(@RequestParam Integer id, @RequestBody @Valid ProductDTO productDTO) {
        ProductDTO updatedProductDTO = productService.updateProduct(id, productDTO);
        return  ResponseEntity.ok(updatedProductDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional(rollbackFor = Throwable.class)
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @GetMapping("/sale")
    public Page<ProductDTO> getPageOfProductsOrderedAscBySale(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        Page<ProductDTO> productDTOPage = productService.findTopProductsBySale(PageRequest.of(page, size));
        return productDTOPage;
    }

    @GetMapping("/new")
    public Page<ProductDTO> getPageOfNewProducts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        Page<ProductDTO> productDTOPage = productService.findPageOfProductsIsNew(PageRequest.of(page, size));
        return productDTOPage;
    }

}


