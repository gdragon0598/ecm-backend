package com.huan.ecommerce.mapper;

import com.huan.ecommerce.dto.ProductDTO;
import com.huan.ecommerce.entity.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;

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
}