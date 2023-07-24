package com.huan.ecommerce.service;

import com.huan.ecommerce.dto.UserDTO;

public interface IUserService {
    public UserDTO findUserById(int id);
}
