package com.huan.ecommerce.service.impl;

import com.huan.ecommerce.dto.OrderDTO;
import com.huan.ecommerce.dto.OrderDetailDTO;
import com.huan.ecommerce.entity.*;
import com.huan.ecommerce.exception.ResourceConflictException;
import com.huan.ecommerce.mapper.EntityDTOMapper;
import com.huan.ecommerce.repository.OrderRepository;
import com.huan.ecommerce.repository.ProductRepository;
import com.huan.ecommerce.repository.UserRepository;
import com.huan.ecommerce.service.IOrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;
    /**
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        Order order = mapOrderDTOAndSetSupplierCustomer(orderDTO);
        List<OrderDetail> orderDetailList = setOrderDetailList(order ,orderDTO);
        orderDetailList.stream().forEach(orderDetail -> {
            checkPriceOfProducts(orderDetail,orderDetail.getProduct());
        });
        order.setTotalAmount(calculateToTalAmount(order));
        order = orderRepository.save(order);
        return EntityDTOMapper.mapOrderToOrderDTO(order);
    }

    private Double calculateToTalAmount(Order order) {
        Double totalAmount = order.getOrderDetailList()
                .parallelStream()
                .map(orderDetail -> orderDetail.getProduct().getPrice() * orderDetail.getQuantity())
                .reduce(0.0,(a,b) -> a+b);
        if(!totalAmount.equals(order.getTotalAmount()))
            throw new ResourceConflictException("Total amount of the order got updated!");
        return totalAmount;
    }
    private Order mapOrderDTOAndSetSupplierCustomer(OrderDTO orderDTO) {
        Supplier supplier = (Supplier) userRepository.findById(orderDTO.getSupplierId().intValue())
                .orElseThrow(() -> new EntityNotFoundException("cannot find supplier with the given id"));
        Customer customer = (Customer) userRepository.findById(orderDTO.getCustomerId().intValue())
                .orElseThrow(() -> new EntityNotFoundException("cannot find customer with the given id"));
        Order order = EntityDTOMapper.mapOrderDTOToOrder(orderDTO);
        order.setSupplier(supplier);
        order.setCustomer(customer);
        return order;
    }
    private List<OrderDetail> setOrderDetailList(Order order, OrderDTO orderDTO) {
        List<OrderDetailDTO> orderDetailDTOList = orderDTO.getOrderDetailList();
        List<OrderDetail> orderDetailList = order.getOrderDetailList();
        for(int i = 0; i < orderDetailDTOList.size(); ++i) {
            Product product = productRepository.findById(orderDetailDTOList.get(i).getProductId().intValue())
                    .orElseThrow(() -> new EntityNotFoundException("cannot find product with the given id"));
            OrderDetail orderDetail = orderDetailList.get(i);
            isEnoughItemsRemained(orderDetail, product);
            orderDetailList.get(i).setProduct(product);
        }
        return orderDetailList;
    }
    private void checkPriceOfProducts(OrderDetail orderDetail, Product product) {
        if (!orderDetail.getPrice().equals(orderDetail.getProduct().getPrice().intValue()))
            throw new ResourceConflictException("Price of a product updated!");
    }
    private void isEnoughItemsRemained(OrderDetail orderDetail, Product product) {
        if(orderDetail.getQuantity() > product.getTotal())
            throw new ResourceConflictException("Out of stock product with id " + product.getId());
    }
}
