package com.huan.ecommerce.service.impl;

import com.huan.ecommerce.dto.UserDTO;
import com.huan.ecommerce.mapper.EntityDTOMapper;
import com.huan.ecommerce.repository.UserRepository;
import com.huan.ecommerce.service.IUserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    public UserDTO findUserById(int id) {
        return EntityDTOMapper.mapUserToDTO(userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException()));
    }

}
