package com.huan.ecommerce.service;

import com.huan.ecommerce.dto.UserDTO;
import com.huan.ecommerce.dto.UserUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    UserDTO findUserById(int id);
    UserDTO saveUser(UserDTO userDTO);
    UserDTO updateUser(int id, UserUpdateDTO userUpdateDTO);

    Page<UserDTO> getPageOfUsers(Pageable pageable);
}
