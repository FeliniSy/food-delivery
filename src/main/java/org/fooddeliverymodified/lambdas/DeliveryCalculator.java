package org.fooddeliverymodified.lambdas;

import java.math.BigDecimal;

@FunctionalInterface
public interface DeliveryCalculator {

    double calculate(double distanceKM, BigDecimal price);
}
