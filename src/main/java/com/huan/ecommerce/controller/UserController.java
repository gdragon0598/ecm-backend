package com.huan.ecommerce.controller;

import com.huan.ecommerce.dto.UserDTO;
import com.huan.ecommerce.dto.UserUpdateDTO;
import com.huan.ecommerce.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping("/{id}")
    public UserDTO getUserByID(@PathVariable int id) {
        UserDTO userDTO = userService.findUserById(id);
        return userDTO;
    }
    @PostMapping
    public UserDTO addUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @PutMapping("/{id}")
    public UserDTO updateUserById(@PathVariable int id, @RequestBody @Valid UserUpdateDTO userUpdateDTO) {
        return userService.updateUser(id, userUpdateDTO);
    }

    @GetMapping
    public Page<UserDTO> getPageOfUsers(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size
    ) {
        return userService.getPageOfUsers(PageRequest.of(page, size));
    }

}
