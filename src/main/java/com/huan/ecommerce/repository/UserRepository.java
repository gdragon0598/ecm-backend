package com.huan.ecommerce.repository;

import com.huan.ecommerce.entity.Customer;
import com.huan.ecommerce.entity.Supplier;
import com.huan.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

}
