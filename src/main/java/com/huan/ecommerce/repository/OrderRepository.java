package com.huan.ecommerce.repository;

import com.huan.ecommerce.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findBySupplierId(Long supplierId, Pageable pageable);

   Page<Order> findByCustomerId(Long customerId, Pageable pageable);
}
