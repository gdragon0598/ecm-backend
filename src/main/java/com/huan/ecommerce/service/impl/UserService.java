package com.huan.ecommerce.service.impl;

import com.huan.ecommerce.entity.User;
import com.huan.ecommerce.repository.UserRepository;
import com.huan.ecommerce.service.IUserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    public User findUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }
}
