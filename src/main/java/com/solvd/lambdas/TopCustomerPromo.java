package com.solvd.lambdas;

import com.solvd.order.Order;

@FunctionalInterface
public interface TopCustomerPromo {

    double calculateWithPromoCode(Order order);
}
