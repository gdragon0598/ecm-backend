package com.huan.ecommerce.mapper;

import com.huan.ecommerce.dto.*;
import com.huan.ecommerce.entity.*;
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
    public static ProductDetail mapProductDetailDTOToProductDetail(ProductDetailDTO productDetailDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<ProductDetailDTO, ProductDetail> typeMap = modelMapper.typeMap(ProductDetailDTO.class, ProductDetail.class);
        typeMap.addMappings(mapper -> mapper.skip(ProductDetail::setProduct));
        return modelMapper.map(productDetailDTO, ProductDetail.class);
    }

    public static ProductDetailDTO mapProductDetailToProductDetailDTO(ProductDetail productDetail) {
        return modelMapper.map(productDetail, ProductDetailDTO.class);
    }

    public static ProductImage mapProductImageDTOToProductImage(ProductImageDTO productImageDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<ProductImageDTO, ProductImage> typeMap = modelMapper.typeMap(ProductImageDTO.class, ProductImage.class);
        typeMap.addMappings(mapper -> mapper.skip(ProductImage::setProductDetail));
        return modelMapper.map(productImageDTO, ProductImage.class);
    }

    public static ProductImageDTO mapProductImageToProductImageDTO(ProductImage productImage) {
        return modelMapper.map(productImage, ProductImageDTO.class);
    }
}