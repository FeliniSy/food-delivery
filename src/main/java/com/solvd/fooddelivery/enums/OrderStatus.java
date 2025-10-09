package com.solvd.fooddelivery.enums;

public enum OrderStatus {

    NEW("Order created"),
    IN_PROGRESS("Order is being prepared"),
    DELIVERING("On the way"),
    COMPLETED("Delivered successfully"),
    CANCELED("Canceled by customer");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
