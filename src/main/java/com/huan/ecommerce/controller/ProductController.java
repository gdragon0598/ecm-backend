package com.huan.ecommerce.controller;


import com.huan.ecommerce.dto.ProductDTO;
import com.huan.ecommerce.entity.Product;
import com.huan.ecommerce.mapper.EntityDTOMapper;
import com.huan.ecommerce.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
        Page<Product> products = productService.findAll(pageable);
        Page<ProductDTO> productDTOPage = products.map(EntityDTOMapper::mapProductToDTO);
        return productDTOPage;
    }
    @PostMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String addProduct(@RequestBody ProductDTO productDTO) {
        Product savedProduct = productService.saveProduct(productDTO);
        return "Product added successfully";
    }
    @PutMapping("/{productId}/price")
    public String updateProductPrice(@PathVariable Long productId, @RequestParam double newPrice) {
            Product updatedProduct = productService.updateProductPrice(productId, newPrice);
            return "Product price updated successfully. New price: " + updatedProduct.getPrice();
    }
    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Integer id) {
        Product product = productService.findProductById(id);
        return EntityDTOMapper.mapProductToDTO(product);
    }
    @GetMapping("/category")
    public Page<ProductDTO> getPageProductsByCategoryId(
            @RequestParam Integer id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<ProductDTO> productDTOPage = productService.findProductByCategoryId(id,pageable).map(EntityDTOMapper::mapProductToDTO);
        return productDTOPage;

    }
    @GetMapping("/brand")
    public Page<ProductDTO> getProductsByBrandId(
            @RequestParam Integer id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<ProductDTO> productDTOPage = productService.findProductByBrandId(id, pageable).map(EntityDTOMapper::mapProductToDTO);
        return productDTOPage;
    }
}


