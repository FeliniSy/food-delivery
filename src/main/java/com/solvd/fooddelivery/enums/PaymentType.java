package com.solvd.fooddelivery.enums;

public enum PaymentType {

    CASH(true), CARD(false), ONLINE(false);

    private final boolean requiresChange;

    PaymentType(boolean requiresChange) {
        this.requiresChange = requiresChange;
    }

    public boolean isCashBased() {
        return requiresChange;
    }
}
