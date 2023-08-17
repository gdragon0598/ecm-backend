package com.huan.ecommerce.mapper;

import com.huan.ecommerce.dto.AddProductDTO;
import com.huan.ecommerce.entity.Brand;
import com.huan.ecommerce.entity.Category;
import com.huan.ecommerce.entity.Product;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EntityDTOMapperTest {
    @Mock
    Category category;

    @Mock
    Brand brand;
    @Mock
    Product product;

    @Mock
    AddProductDTO addProductDTO;

    @Mock
    EntityDTOMapper entityDTOMapper;

    public EntityDTOMapperTest() {
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


        when(addProductDTO.getName()).thenReturn("test");
        when(addProductDTO.getCategoryId()).thenReturn(Long.valueOf(1));
        when(addProductDTO.getBrandId()).thenReturn(Long.valueOf(1));
        when(addProductDTO.getPrice()).thenReturn(Double.valueOf(1000));
        when(addProductDTO.getImageUrl()).thenReturn("test url");
        when(addProductDTO.getSale()).thenReturn(Integer.valueOf(0));

       AddProductDTO actualAddProductDTO = entityDTOMapper.mapProductToDTO(product);

       assertEquals(addProductDTO.getSale(), actualAddProductDTO.getSale());
        assertEquals(addProductDTO.getName(), actualAddProductDTO.getName());
        assertEquals(addProductDTO.getPrice(), actualAddProductDTO.getPrice());
        assertEquals(addProductDTO.getCategoryId(), actualAddProductDTO.getCategoryId());
        assertEquals(addProductDTO.getBrandId(), actualAddProductDTO.getBrandId());
        assertEquals(addProductDTO.getImageUrl(), actualAddProductDTO.getImageUrl());
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


        when(addProductDTO.getName()).thenReturn("test");
        when(addProductDTO.getCategoryId()).thenReturn(Long.valueOf(1));
        when(addProductDTO.getBrandId()).thenReturn(Long.valueOf(1));
        when(addProductDTO.getPrice()).thenReturn(Double.valueOf(1000));
        when(addProductDTO.getImageUrl()).thenReturn("test url");
        when(addProductDTO.getSale()).thenReturn(Integer.valueOf(0));

        Product actualProduct = entityDTOMapper.mapProductDTOToEntity(addProductDTO);

        assertEquals(product.getSale(), actualProduct.getSale());
        assertEquals(product.getName(), actualProduct.getName());
        assertEquals(product.getPrice(), actualProduct.getPrice());
        assertEquals(product.getCategory(), actualProduct.getCategory());
        assertEquals(product.getBrand(), actualProduct.getBrand());
        assertEquals(product.getImageUrl(), actualProduct.getImageUrl());
    }

}