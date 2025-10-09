package com.solvd.lambdas;

import com.solvd.order.Order;

@FunctionalInterface
public interface OrderValidator {

    boolean validate(Order order);
}
