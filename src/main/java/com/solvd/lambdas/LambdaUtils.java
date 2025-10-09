package com.solvd.lambdas;

import com.solvd.order.Order;

import java.util.Random;
import java.util.function.*;

public class LambdaUtils {
    public static final Predicate<Order> EXPENSIVE_ORDER =
            order -> order.calculateTotal().doubleValue() > 50.0;

    public static final Function<Order, String> CUSTOMER_NAME =
            order -> order.getCustomer().getName();

    public static final Supplier<Integer> DELIVERY_TIME =
            () -> new Random().nextInt(60);

    public static final Consumer<Order> PRINT_ORDER =
            order -> System.out.println("Order: " + order);

    public static final BiFunction<Double, Double, Double> ADD_DELIVERY_FEE =
            Double::sum;
}
