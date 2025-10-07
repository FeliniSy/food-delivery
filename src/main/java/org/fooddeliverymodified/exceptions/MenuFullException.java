package org.fooddeliverymodified.exceptions;

public class MenuFullException extends RuntimeException {

    public MenuFullException(String message) {
        super(message);
    }
}
