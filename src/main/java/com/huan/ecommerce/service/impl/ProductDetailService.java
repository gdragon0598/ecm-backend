package com.huan.ecommerce.service.impl;

import com.huan.ecommerce.dto.ProductDetailDTO;
import com.huan.ecommerce.entity.Product;
import com.huan.ecommerce.entity.ProductDetail;
import com.huan.ecommerce.mapper.EntityDTOMapper;
import com.huan.ecommerce.repository.ProductDetailRepository;
import com.huan.ecommerce.repository.ProductRepository;
import com.huan.ecommerce.service.IProductDetailService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailService implements IProductDetailService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;
    /**
     *
     * @param productDetailDTO
     * @return
     */
    @Override
    public ProductDetailDTO saveProduct(ProductDetailDTO productDetailDTO) {
        Long productId = productDetailDTO.getProductId();
        if(!productRepository.existsById(productId.intValue())) {
            throw new EntityNotFoundException("Product does not exists. Product ID: " + productId);
        }
        if (productDetailRepository.existsProductDetailByProductId(productId)) {
               throw new EntityExistsException("Product detail with the given product_id already exists. Product ID: " + productId);
        }
        Product product = productRepository.getReferenceById(productDetailDTO.getProductId().intValue());
        ProductDetail productDetail = EntityDTOMapper.mapProductDetailDTOToProductDetail(productDetailDTO);
        productDetail.setProduct(product);
        return EntityDTOMapper.mapProductDetailToProductDetailDTO(productDetailRepository.save(productDetail));
    }

    /**
     *
     * @param productDetailDTO
     */
    @Override
    public ProductDetailDTO updateProduct(ProductDetailDTO productDetailDTO) {
        Long productId = productDetailDTO.getProductId();
        if(!productRepository.existsById(productId.intValue())) {
            throw new EntityNotFoundException("Product does not exists. Product ID: " + productId);
        }
        ProductDetail productDetail = productDetailRepository.findProductDetailByProductId(productId)
                                                            .orElseThrow(() -> new EntityNotFoundException("product detail of product not found. Product Id"+ productId));
        productDetail.setDescription(productDetailDTO.getDescription());
        productDetail.setAdditionalInformation(productDetailDTO.getAdditionalInformation());
        return EntityDTOMapper.mapProductDetailToProductDetailDTO(productDetailRepository.save(productDetail));
    }

    /**
     * @param productId
     * @return
     */
    @Override
    public ProductDetailDTO getProductDetailByProductId(Long productId) {
        return EntityDTOMapper.mapProductDetailToProductDetailDTO(productDetailRepository.findProductDetailByProductId(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product detail with the given product id does not exist. Product id: "+ productId)));
    }
}
