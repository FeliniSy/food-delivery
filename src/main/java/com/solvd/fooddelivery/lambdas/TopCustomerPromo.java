package com.solvd.fooddelivery.lambdas;

import com.solvd.fooddelivery.order.Order;

@FunctionalInterface
public interface TopCustomerPromo {

    double calculateWithPromoCode(Order order);
}
