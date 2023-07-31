package com.huan.ecommerce.dto;

import com.huan.ecommerce.common.ValidOrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    @Null(message = "id must not be included in the request")
    private Long id;

    @NotNull(message = "supplier id must not be null")
    private Long supplierId;

    @NotNull(message = "total amount must not be null")
    private Double totalAmount;

    @ValidOrderStatus
    private String status;

    @NotNull(message = "user id must be set and not null in the request")
    private Long customerId;

    @Valid
    @NotEmpty(message = "order detail list must be not empty")
    private List<OrderDetailDTO> orderDetailList;
}
