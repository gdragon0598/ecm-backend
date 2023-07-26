package com.huan.ecommerce.repository;

import com.huan.ecommerce.entity.UserRole;
import com.huan.ecommerce.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}
