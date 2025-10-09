package com.solvd.enums;

public enum Discount {

    NONE(0),
    PROMOCODE(15),
    STUDENT(25),
    BLACKFRIDAY(60),
    HOLIDAY(40);

    private final int percentage;

    Discount(int percentage) {
        this.percentage = percentage;
    }

    public double getDiscount(double price) {
        return price - (price * percentage) / 100.0;
    }

    public double getDiscount() {
        return percentage / 100.0;
    }
}
