package com.huan.ecommerce.dto;

import com.huan.ecommerce.common.ValidOrderStatus;
import lombok.Data;

@Data
public class OrderStatusDTO {
    /**
     * This DTO class is to update the status of an order with help of @ValidOrderStatus to validate the status field
     */
    @ValidOrderStatus
    private String status;
}
