package com.huan.ecommerce.controller;

import com.huan.ecommerce.common.JwtTokenUtil;
import com.huan.ecommerce.dto.*;
import com.huan.ecommerce.entity.User;
import com.huan.ecommerce.security.CustomUserDetails;
import com.huan.ecommerce.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/{id}")
    public UserDTO getUserByID(@PathVariable int id) {
        UserDTO userDTO = userService.findUserById(id);
        return userDTO;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO registerDTO) {
        User existingUser = userService.findUserByEmail(registerDTO.getEmail());
        if (existingUser != null) {
            return ResponseEntity.badRequest().body("An account with the same email already exists.");
        }
        userService.saveUser(registerDTO);
        return ResponseEntity.ok("Registration successful.");
    }

    @PostMapping(path= "/login", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> login(@Valid AuthRequestDTO request) {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword()));

            CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
            String accessToken = jwtTokenUtil.generateAccessToken(user);
            AuthResponseDTO response = new AuthResponseDTO(user.getUsername(), accessToken);
            return ResponseEntity.ok().body(response);
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

    @PutMapping("/{userId}/role")
    public UserDTO updateUserRole(@PathVariable int userId, @RequestBody @Valid UserRoleUpdateDTO userRoleUpdateDTO) {
        return userService.updateUserRole(userId, userRoleUpdateDTO);
    }

}
