package com.huan.ecommerce.mapper;

import com.huan.ecommerce.dto.BrandDTO;
import com.huan.ecommerce.dto.CategoryDTO;
import com.huan.ecommerce.dto.ProductDTO;
import com.huan.ecommerce.dto.UserDTO;
import com.huan.ecommerce.entity.Brand;
import com.huan.ecommerce.entity.Category;
import com.huan.ecommerce.entity.Product;
import com.huan.ecommerce.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.diagnostics.analyzer.BeanNotOfRequiredTypeFailureAnalyzer;

public class EntityDTOMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public static ProductDTO mapProductToDTO(Product entity) {
        return modelMapper.map(entity, ProductDTO.class);
    }
    public static Product mapProductDTOToEntity(ProductDTO productDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<ProductDTO, Product> typeMap = modelMapper.typeMap(ProductDTO.class, Product.class);
        typeMap.addMappings(mapper -> mapper.skip(Product::setCategory))
                .addMappings(mapper -> mapper.skip(Product::setBrand))
                .addMappings(mapper -> mapper.skip(Product::setSupplier));

        Product mappedProduct = modelMapper.map(productDTO, Product.class);
        return mappedProduct;
    }

    public static UserDTO mapUserToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
    public static CategoryDTO mapCategoryToDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    public static BrandDTO mapBrandToDTO(Brand brand) {
        return modelMapper.map(brand, BrandDTO.class);
    }
}