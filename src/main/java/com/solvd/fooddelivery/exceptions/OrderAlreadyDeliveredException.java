package com.solvd.fooddelivery.exceptions;

public class OrderAlreadyDeliveredException extends RuntimeException {

    public OrderAlreadyDeliveredException(String message) {
        super(message);
    }
}
