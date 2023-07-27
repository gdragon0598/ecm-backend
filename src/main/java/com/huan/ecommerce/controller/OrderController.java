package com.huan.ecommerce.controller;

import com.huan.ecommerce.dto.OrderDTO;
import com.huan.ecommerce.dto.OrderDetailDTO;
import com.huan.ecommerce.dto.UserDTO;
import com.huan.ecommerce.service.IOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @PostMapping
    public OrderDTO addOrder(@RequestBody @Valid OrderDTO orderDTO) {
        return orderService.saveOrder(orderDTO);
    }
}
