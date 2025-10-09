package com.solvd.fooddelivery.exceptions;

public class InvalidPaymentAmountException extends RuntimeException {

    public InvalidPaymentAmountException(String message) {
        super(message);
    }
}
