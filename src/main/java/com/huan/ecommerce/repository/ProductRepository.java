package com.huan.ecommerce.repository;

import com.huan.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT p FROM Product p WHERE p.category.id = ?1")
    Collection<Product> findProductsByCategoryId(int categoryId);
}
