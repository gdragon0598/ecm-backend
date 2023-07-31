package com.huan.ecommerce.controller;

import com.huan.ecommerce.dto.OrderDTO;
import com.huan.ecommerce.dto.OrderStatusDTO;
import com.huan.ecommerce.service.IOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO addOrder(@RequestBody @Valid OrderDTO orderDTO) {
        return orderService.saveOrder(orderDTO);
    }

    @GetMapping("/supplier/{supplierId}")
    public Page<OrderDTO> getPageOfOrderBySupplierId(
            @PathVariable Long supplierId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return orderService.findPageOfOrdersBySupplierId(supplierId, PageRequest.of(page, size));
    }

    @GetMapping("/customer/{customerId}")
    public Page<OrderDTO> getPageOfOrderByCustomerId(
            @PathVariable Long customerId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return orderService.findPageOfOrdersByCustomerId(customerId, PageRequest.of(page, size));
    }

    @PutMapping("/{orderId}/status")
    public OrderDTO updateOrderStatus(
            @PathVariable Long orderId,
            @Valid OrderStatusDTO newStatus
    ) {
       return orderService.updateOrderStatus(orderId, newStatus);
    }
}
