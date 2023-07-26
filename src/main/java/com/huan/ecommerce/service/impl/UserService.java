package com.huan.ecommerce.service.impl;

import com.huan.ecommerce.dto.AddressDTO;
import com.huan.ecommerce.dto.UserDTO;
import com.huan.ecommerce.dto.UserUpdateDTO;
import com.huan.ecommerce.entity.Address;
import com.huan.ecommerce.entity.Role;
import com.huan.ecommerce.entity.User;
import com.huan.ecommerce.entity.UserRole;
import com.huan.ecommerce.mapper.EntityDTOMapper;
import com.huan.ecommerce.repository.RoleRepository;
import com.huan.ecommerce.repository.UserRepository;
import com.huan.ecommerce.service.IUserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserDTO findUserById(int id) {
        return EntityDTOMapper.mapUserToDTO(userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException()));
    }

    /**
     * @param userDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public UserDTO saveUser(UserDTO userDTO) {
        User user = EntityDTOMapper.mapUserDTOToUser(userDTO);
        List<Role> roleList = userDTO.getRoleIdList()
                        .stream()
                        .map(roleId -> roleRepository.findById(roleId).orElseThrow(() -> new EntityNotFoundException("There is no role with that id" + roleId))).toList();
        List<UserRole> userRoleList = new ArrayList<>();
        for (Role role : roleList) {
            userRoleList.add(new UserRole());
            userRoleList.get(userRoleList.size() - 1).setUser(user);
            userRoleList.get(userRoleList.size() - 1).setRole(role);
        }
        Set<UserRole> userRoleSet = user.getUserRoleSet();
        userRoleList.stream().forEach(userRole -> userRoleSet.add(userRole));
        User savedUser = userRepository.save(user);
        return EntityDTOMapper.mapUserToDTO(savedUser);
    }

    /**
     * @param id
     * @param userUpdateDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public UserDTO updateUser(int id, UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found user with given id"));
        updateUserBasicInfo(user,userUpdateDTO);
        user = userRepository.save(user);
        return EntityDTOMapper.mapUserToDTO(user);
    }

    /**
     * @return
     */
    @Override
    public Page<UserDTO> getPageOfUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(EntityDTOMapper::mapUserToDTO);
    }

    private void updateUserBasicInfo(User user, UserUpdateDTO userUpdateDTO) {
        Address address = user.getAddress();
        AddressDTO addressDTO = userUpdateDTO.getAddress();
        address.setCity(addressDTO.getCity());
        address.setStreet(addressDTO.getStreet());
        address.setProvince(addressDTO.getProvince());
        address.setPostalCode(addressDTO.getPostalCode());
        user.setFirstName(userUpdateDTO.getFirstName());
        user.setLastName(userUpdateDTO.getLastName());
        user.setDOB(userUpdateDTO.getDOB());
    }

}
