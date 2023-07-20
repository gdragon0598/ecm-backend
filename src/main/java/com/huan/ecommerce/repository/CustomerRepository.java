package com.huan.ecommerce.repository;

import com.huan.ecommerce.entity.Customer;
import com.huan.ecommerce.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}

