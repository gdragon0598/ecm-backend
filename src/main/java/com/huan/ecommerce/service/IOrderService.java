package com.huan.ecommerce.service;

import com.huan.ecommerce.dto.OrderDTO;

public interface IOrderService {

    OrderDTO saveOrder(OrderDTO orderDTO);
}
