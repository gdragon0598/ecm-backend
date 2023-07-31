package com.huan.ecommerce.common;

import com.huan.ecommerce.entity.OrderStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.EnumSet;

public class OrderStatusValidator implements ConstraintValidator<ValidOrderStatus, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return EnumSet.allOf(OrderStatus.class).stream()
                .map(Enum::name)
                .anyMatch(enumValue -> enumValue.equals(value));
    }
}

