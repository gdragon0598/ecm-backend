package com.huan.ecommerce.service;

import com.huan.ecommerce.dto.OrderDTO;
import com.huan.ecommerce.dto.OrderStatusDTO;
import com.huan.ecommerce.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService {

    OrderDTO saveOrder(OrderDTO orderDTO);

    Page<OrderDTO> findPageOfOrdersBySupplierId(Long supplierId, Pageable pageable);

    Page<OrderDTO> findPageOfOrdersByCustomerId(Long customerId, Pageable pageable);

    OrderDTO updateOrderStatus(Long orderId, OrderStatusDTO newStatus);
}
