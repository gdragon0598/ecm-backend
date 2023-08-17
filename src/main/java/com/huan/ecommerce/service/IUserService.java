package com.huan.ecommerce.service;

import com.huan.ecommerce.dto.RegisterDTO;
import com.huan.ecommerce.dto.UserDTO;
import com.huan.ecommerce.dto.UserRoleUpdateDTO;
import com.huan.ecommerce.dto.UserUpdateDTO;
import com.huan.ecommerce.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    UserDTO findUserById(int id);

    User findUserByEmail(String email);
    UserDTO saveUser(RegisterDTO registerDTO);
    UserDTO updateUser(int id, UserUpdateDTO userUpdateDTO);

    Page<UserDTO> getPageOfUsers(Pageable pageable);

    UserDTO updateUserRole(int userId, UserRoleUpdateDTO roleId);
}
