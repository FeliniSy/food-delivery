package com.solvd.fooddelivery.lambdas;

import com.solvd.fooddelivery.order.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.function.*;

public class LambdaUtils {

    private static final Logger logger = LogManager.getLogger(LambdaUtils.class);

    public static final Predicate<Order> EXPENSIVE_ORDER =
            order -> order.calculateTotal().doubleValue() > 50.0;

    public static final Function<Order, String> CUSTOMER_NAME =
            order -> order.getCustomer().getName();

    public static final Supplier<Integer> DELIVERY_TIME =
            () -> new Random().nextInt(60);

    public static final Consumer<Order> PRINT_ORDER =
            order -> logger.info(order.toString());

    public static final BiFunction<Double, Double, Double> ADD_DELIVERY_FEE =
            Double::sum;
}
