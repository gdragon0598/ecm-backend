package com.huan.ecommerce.repository;

import com.huan.ecommerce.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    @Query(value = "SELECT pi FROM ProductImage pi WHERE pi.productDetail.product.id = :productId")
    List<ProductImage> findProductImagesByProductId(Long productId);
}
