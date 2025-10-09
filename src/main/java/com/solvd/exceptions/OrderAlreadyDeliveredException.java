package com.solvd.exceptions;

public class OrderAlreadyDeliveredException extends RuntimeException {

    public OrderAlreadyDeliveredException(String message) {
        super(message);
    }
}
