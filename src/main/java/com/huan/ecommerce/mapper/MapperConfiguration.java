package com.huan.ecommerce.mapper;

import com.huan.ecommerce.dto.*;
import com.huan.ecommerce.entity.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        //Product to AddProductDTO
        TypeMap<Product, AddProductDTO> typeMap = modelMapper.typeMap(Product.class, AddProductDTO.class);
        typeMap.addMappings(mapper -> mapper.map(src -> src.getBrand().getId(), AddProductDTO::setBrandId))
                .addMappings(mapper -> mapper.map(src -> src.getCategory().getId(), AddProductDTO::setCategoryId))
                .addMappings(mapper -> mapper.map(src -> src.getSupplier().getId(), AddProductDTO::setSupplierId));

        //AddProductDTO to Product
        TypeMap<AddProductDTO, Product> typeMap2 = modelMapper.typeMap(AddProductDTO.class, Product.class);
        typeMap2.addMappings(mapper -> mapper.skip(Product::setCategory))
                .addMappings(mapper -> mapper.skip(Product::setBrand))
                .addMappings(mapper -> mapper.skip(Product::setSupplier));

        //User to UserDTO
        TypeMap<User, UserDTO> typeMap3 = modelMapper.typeMap(User.class, UserDTO.class);

        //UserDTO to User
        TypeMap<UserDTO, User> typeMap4 = modelMapper.typeMap(UserDTO.class, User.class);
        typeMap4.addMappings(mapper -> mapper.skip(User::setUserRoleSet));

        //OrderDTO to Order
        TypeMap<OrderDTO, Order> typeMap5 = modelMapper.typeMap(OrderDTO.class, Order.class);
        typeMap5.addMappings(mapper -> mapper.skip(Order::setSupplier))
                .addMappings(mapper -> mapper.skip(Order::setCustomer));

        //Order to OrderDTO
        TypeMap<Order, OrderDTO> typeMap6 = modelMapper.typeMap(Order.class, OrderDTO.class);
        typeMap6.addMappings(mapper -> mapper.map(src -> src.getSupplier().getId(), OrderDTO::setSupplierId))
                .addMappings(mapper -> mapper.map(src -> src.getCustomer().getId(), OrderDTO::setCustomerId));
        TypeMap<OrderDetail, OrderDetailDTO> typeMap7 = modelMapper.typeMap(OrderDetail.class, OrderDetailDTO.class);
        typeMap7.addMappings(mapper -> mapper.map(src -> src.getProduct().getId(), OrderDetailDTO::setProductId))
                .addMappings(mapper -> mapper.map(src -> src.getProduct().getImageUrl(), OrderDetailDTO::setImageUrl));

        //Comment to CommentDTO
        TypeMap<Comment, CommentDTO> typeMap8 = modelMapper.typeMap(Comment.class, CommentDTO.class);
        typeMap8.addMappings(mapper -> mapper.map(src -> {
            User user = src.getUser();
            return user.getFullName();
        }, CommentDTO::setUserFullName));

        //Product to DetailedProductDTO
        TypeMap<Product, DetailedProductDTO> typeMap9 = modelMapper.typeMap(Product.class, DetailedProductDTO.class);
        typeMap9.addMappings(mapper -> mapper.map(src -> src.getBrand().getId(), DetailedProductDTO::setBrandId))
                .addMappings(mapper -> mapper.map(src -> src.getCategory().getId(), DetailedProductDTO::setCategoryId))
                .addMappings(mapper -> mapper.map(src -> src.getSupplier().getId(), DetailedProductDTO::setSupplierId))
                .addMappings(mapper -> mapper.map(src -> src.getBrand().getName(), DetailedProductDTO::setBrand))
                .addMappings(mapper -> mapper.map(src -> src.getCategory().getName(), DetailedProductDTO::setCategory))
        ;
        return modelMapper;
    }
}
