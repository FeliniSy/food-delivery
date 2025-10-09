package com.solvd.lambdas;

import java.math.BigDecimal;

@FunctionalInterface
public interface DeliveryCalculator {

    double calculate(double distanceKM, BigDecimal price);
}
