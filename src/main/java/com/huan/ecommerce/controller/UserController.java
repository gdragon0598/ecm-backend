package com.huan.ecommerce.controller;

import com.huan.ecommerce.dto.UserDTO;
import com.huan.ecommerce.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping("/{id}")
    public UserDTO getUserByID(@PathVariable int id) {
        UserDTO userDTO = userService.findUserById(id);
        return userDTO;
    }
}
