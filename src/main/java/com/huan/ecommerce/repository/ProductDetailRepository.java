package com.huan.ecommerce.repository;

import com.huan.ecommerce.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

    @Query(value = "SELECT COUNT(pd) > 0 FROM ProductDetail pd WHERE pd.product.id = :productId")
    boolean existsProductDetailByProductId(Long productId);

    @Query(value = "SELECT pd FROM ProductDetail pd WHERE pd.product.id = :productId")
    Optional<ProductDetail> findProductDetailByProductId(Long productId);
}
