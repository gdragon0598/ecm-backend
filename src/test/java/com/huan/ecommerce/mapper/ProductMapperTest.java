package com.huan.ecommerce.mapper;

import com.huan.ecommerce.dto.ProductDTO;
import com.huan.ecommerce.entity.Brand;
import com.huan.ecommerce.entity.Category;
import com.huan.ecommerce.entity.Product;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductMapperTest {
    @Mock
    Category category;

    @Mock
    Brand brand;
    @Mock
    Product product;

    @Mock
    ProductDTO productDTO;

    public ProductMapperTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void mapProductToDTOTest() {
        when(category.getId()).thenReturn(Long.valueOf(1));
        when(brand.getId()).thenReturn(Long.valueOf(1));
       when(product.getId()).thenReturn(Long.valueOf(1));
       when(product.getName()).thenReturn("test");
       when(product.getCategory()).thenReturn(category);
       when(product.getBrand()).thenReturn(brand);
       when(product.getPrice()).thenReturn(Double.valueOf(1000));
       when(product.getImageUrl()).thenReturn("test url");
       when(product.getSale()).thenReturn(Integer.valueOf(0));


        when(productDTO.getName()).thenReturn("test");
        when(productDTO.getCategoryId()).thenReturn(Long.valueOf(1));
        when(productDTO.getBrandId()).thenReturn(Long.valueOf(1));
        when(productDTO.getPrice()).thenReturn(Double.valueOf(1000));
        when(productDTO.getImageUrl()).thenReturn("test url");
        when(productDTO.getSale()).thenReturn(Integer.valueOf(0));

       ProductDTO actualProductDTO = ProductMapper.mapProductToDTO(product);

       assertEquals(productDTO.getSale(), actualProductDTO.getSale());
        assertEquals(productDTO.getName(), actualProductDTO.getName());
        assertEquals(productDTO.getPrice(), actualProductDTO.getPrice());
        assertEquals(productDTO.getCategoryId(), actualProductDTO.getCategoryId());
        assertEquals(productDTO.getBrandId(), actualProductDTO.getBrandId());
        assertEquals(productDTO.getImageUrl(), actualProductDTO.getImageUrl());
    }

    @Test
    public void mapProductDTOToEntityTest() {

        when(category.getId()).thenReturn(Long.valueOf(1));
        when(brand.getId()).thenReturn(Long.valueOf(1));
        when(product.getId()).thenReturn(Long.valueOf(1));
        when(product.getName()).thenReturn("test");
        when(product.getCategory()).thenReturn(category);
        when(product.getBrand()).thenReturn(brand);
        when(product.getPrice()).thenReturn(Double.valueOf(1000));
        when(product.getImageUrl()).thenReturn("test url");
        when(product.getSale()).thenReturn(Integer.valueOf(0));


        when(productDTO.getName()).thenReturn("test");
        when(productDTO.getCategoryId()).thenReturn(Long.valueOf(1));
        when(productDTO.getBrandId()).thenReturn(Long.valueOf(1));
        when(productDTO.getPrice()).thenReturn(Double.valueOf(1000));
        when(productDTO.getImageUrl()).thenReturn("test url");
        when(productDTO.getSale()).thenReturn(Integer.valueOf(0));

        Product actualProduct = ProductMapper.mapProductDTOToEntity(productDTO);

        assertEquals(product.getSale(), actualProduct.getSale());
        assertEquals(product.getName(), actualProduct.getName());
        assertEquals(product.getPrice(), actualProduct.getPrice());
        assertEquals(product.getCategory(), actualProduct.getCategory());
        assertEquals(product.getBrand(), actualProduct.getBrand());
        assertEquals(product.getImageUrl(), actualProduct.getImageUrl());
    }

}