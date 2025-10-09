package com.solvd.fooddelivery.lambdas;

import com.solvd.fooddelivery.order.Order;

@FunctionalInterface
public interface OrderValidator {

    boolean validate(Order order);
}
