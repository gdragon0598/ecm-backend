package com.huan.ecommerce.controller;

import com.huan.ecommerce.dto.AddProductDTO;
import com.huan.ecommerce.dto.DetailedProductDTO;
import com.huan.ecommerce.dto.SimpleResponseProductDTO;
import com.huan.ecommerce.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;


    @GetMapping("")
    public List<SimpleResponseProductDTO> getPageOfProducts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<SimpleResponseProductDTO> productDTOPage = productService.findAll(pageable);
        return productDTOPage;
    }
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AddProductDTO> addProduct(@RequestBody @Valid AddProductDTO addProductDTO) {
        AddProductDTO savedProduct = productService.saveProduct(addProductDTO);
        return ResponseEntity.ok(savedProduct);
    }
    @PutMapping("/{productId}/price")
    public ResponseEntity<AddProductDTO> updateProductPrice(@PathVariable Long productId, @RequestParam double newPrice) {
        AddProductDTO updatedAddProductDTO = productService.updateProductPrice(productId, newPrice);
        return  ResponseEntity.ok(updatedAddProductDTO);
    }
    @GetMapping("/{id}")
    public DetailedProductDTO getProductById(@PathVariable Integer id) {
        DetailedProductDTO addProductDTO = productService.findProductById(id);
        return addProductDTO;
        //DETAILS
    }
    @GetMapping("/category/{categoryId}")
    public Page<SimpleResponseProductDTO> getPageProductsByCategoryId(@PathVariable Long categoryId,
                                                           @RequestParam(value = "page", defaultValue = "0") int page,
                                                           @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<SimpleResponseProductDTO> productDTOPage = productService.findProductByCategoryId(categoryId,pageable);
        return productDTOPage;
    }
    @GetMapping("/brand/{brandId}")
    public Page<SimpleResponseProductDTO> getPageProductsByBrandId(
            @PathVariable Long brandId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<SimpleResponseProductDTO> productDTOPage = productService.findProductByBrandId(brandId, pageable);
        return productDTOPage;
    }

    @PutMapping("")
    public  ResponseEntity<AddProductDTO> updateProductById(@RequestParam Integer id, @RequestBody @Valid AddProductDTO addProductDTO) {
        AddProductDTO updatedAddProductDTO = productService.updateProduct(id, addProductDTO);
        return  ResponseEntity.ok(updatedAddProductDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @GetMapping("/sale")
    public Page<SimpleResponseProductDTO> getPageOfProductsOrderedAscBySale(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        Page<SimpleResponseProductDTO> productDTOPage = productService.findTopProductsBySale(PageRequest.of(page, size));
        return productDTOPage;
    }

    @GetMapping("/new")
    public List<SimpleResponseProductDTO> getPageOfNewProducts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        List<SimpleResponseProductDTO> productDTOPage = productService.findPageOfProductsIsNew(PageRequest.of(page, size));
        return productDTOPage;
    }

}


