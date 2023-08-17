package com.huan.ecommerce.mapper;

import com.huan.ecommerce.dto.*;
import com.huan.ecommerce.entity.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class EntityDTOMapper {
    @Autowired
    private ModelMapper modelMapper;


    public AddProductDTO mapProductToDTO(Product entity) {
        return modelMapper.map(entity, AddProductDTO.class);
    }
    public Product mapProductDTOToEntity(AddProductDTO addProductDTO) {
        Product mappedProduct = modelMapper.map(addProductDTO, Product.class);
        mappedProduct.getProductDetail().setProduct(mappedProduct);
        mappedProduct.getProductDetail().getProductImage().stream().forEach(productImage -> productImage.setProductDetail(mappedProduct.getProductDetail()));
        return mappedProduct;
    }
    public UserDTO mapUserToDTO(User user) {
        UserDTO mappedUserDTO = modelMapper.map(user, UserDTO.class);
        mappedUserDTO.getRoleIdList().addAll(user.getUserRoleSet().stream().map(userRole -> userRole.getRole().getId()).toList());
        return mappedUserDTO;
    }

    public User mapUserDTOToUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.getAddress().setUser(user);
        return user;
    }
    public CategoryDTO mapCategoryToDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }
    public BrandDTO mapBrandToDTO(Brand brand) {
        return modelMapper.map(brand, BrandDTO.class);
    }

    public Order mapOrderDTOToOrder(OrderDTO orderDTO) {
        Order mappedOrder = modelMapper.map(orderDTO, Order.class);
        mappedOrder.getOrderDetailList().stream().forEach(od -> od.setOrder(mappedOrder));
        return mappedOrder;
    }

    public OrderDTO mapOrderToOrderDTO(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }

    public SimpleResponseProductDTO mapProductToSimpleResponseDTO(Product product) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(product, SimpleResponseProductDTO.class);
    }

    public CommentDTO mapCommentToCommentDTO(Comment comment) {
        return modelMapper.map(comment, CommentDTO.class);
    }

    public DetailedProductDTO mapProductToDetailedDTO(Product product) {
        return modelMapper.map(product, DetailedProductDTO.class);
    }

    public User mapRegisterDTOToUser(RegisterDTO registerDTO) {
        return modelMapper.map(registerDTO, User.class);
    }

}