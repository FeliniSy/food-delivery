package com.solvd.fooddelivery.enums;

public enum DeliverySpeed {

    STANDARD(40), EXPRESS(15), ECONOMY(60);

    private final int minutes;

    DeliverySpeed(int minutes) {
        this.minutes = minutes;
    }

    public int getMinutes() {
        return minutes;
    }
}
