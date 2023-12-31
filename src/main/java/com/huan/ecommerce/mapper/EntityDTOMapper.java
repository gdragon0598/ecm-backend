package com.huan.ecommerce.mapper;

import com.huan.ecommerce.dto.*;
import com.huan.ecommerce.entity.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;


public class EntityDTOMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public static ProductDTO mapProductToDTO(Product entity) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<Product, ProductDTO> typeMap = modelMapper.typeMap(Product.class, ProductDTO.class);
        typeMap.addMappings(mapper -> mapper.map(src -> src.getBrand().getId(), ProductDTO::setBrandId))
                .addMappings(mapper -> mapper.map(src -> src.getCategory().getId(), ProductDTO::setCategoryId))
                .addMappings(mapper -> mapper.map(src -> src.getSupplier().getId(), ProductDTO::setSupplierId));
        return modelMapper.map(entity, ProductDTO.class);
    }
    public static Product mapProductDTOToEntity(ProductDTO productDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<ProductDTO, Product> typeMap = modelMapper.typeMap(ProductDTO.class, Product.class);
        typeMap.addMappings(mapper -> mapper.skip(Product::setCategory))
                .addMappings(mapper -> mapper.skip(Product::setBrand))
                .addMappings(mapper -> mapper.skip(Product::setSupplier));

        Product mappedProduct = modelMapper.map(productDTO, Product.class);
        mappedProduct.getProductDetail().setProduct(mappedProduct);
        mappedProduct.getProductDetail().getProductImage().stream().forEach(productImage -> productImage.setProductDetail(mappedProduct.getProductDetail()));
        return mappedProduct;
    }
    public static UserDTO mapUserToDTO(User user) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<User, UserDTO> typeMap = modelMapper.typeMap(User.class, UserDTO.class);
        UserDTO mappedUserDTO = modelMapper.map(user, UserDTO.class);
        mappedUserDTO.getRoleIdList().addAll(user.getUserRoleSet().stream().map(userRole -> userRole.getRole().getId()).toList());
        return mappedUserDTO;
    }

    public static User mapUserDTOToUser(UserDTO userDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<UserDTO, User> typeMap = modelMapper.typeMap(UserDTO.class, User.class);
        typeMap.addMappings(mapper -> mapper.skip(User::setUserRoleSet));
        User user = modelMapper.map(userDTO, User.class);
        user.getAddress().setUser(user);
        return user;
    }
    public static CategoryDTO mapCategoryToDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }
    public static BrandDTO mapBrandToDTO(Brand brand) {
        return modelMapper.map(brand, BrandDTO.class);
    }

    public static Order mapOrderDTOToOrder(OrderDTO orderDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<OrderDTO, Order> typeMap = modelMapper.typeMap(OrderDTO.class, Order.class);
        typeMap.addMappings(mapper -> mapper.skip(Order::setSupplier))
                .addMappings(mapper -> mapper.skip(Order::setCustomer));
        Order mappedOrder = modelMapper.map(orderDTO, Order.class);
        mappedOrder.getOrderDetailList().stream().forEach(od -> od.setOrder(mappedOrder));
        return mappedOrder;
    }

    public static OrderDTO mapOrderToOrderDTO(Order order) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<Order, OrderDTO> typeMap = modelMapper.typeMap(Order.class, OrderDTO.class);
        typeMap.addMappings(mapper -> mapper.map(src -> src.getSupplier().getId(), OrderDTO::setSupplierId))
                .addMappings(mapper -> mapper.map(src -> src.getCustomer().getId(), OrderDTO::setCustomerId));
        TypeMap<OrderDetail, OrderDetailDTO> typeMap1 = modelMapper.typeMap(OrderDetail.class, OrderDetailDTO.class);
        typeMap1.addMappings(mapper -> mapper.map(src -> src.getProduct().getId(), OrderDetailDTO::setProductId))
                .addMappings(mapper -> mapper.map(src -> src.getProduct().getImageUrl(), OrderDetailDTO::setImageUrl));
        return modelMapper.map(order, OrderDTO.class);
    }
}