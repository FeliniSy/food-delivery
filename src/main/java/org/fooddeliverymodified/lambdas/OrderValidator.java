package org.fooddeliverymodified.lambdas;

import org.fooddeliverymodified.order.Order;

@FunctionalInterface
public interface OrderValidator {

    boolean validate(Order order);
}
