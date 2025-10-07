package org.fooddeliverymodified.enums;

public enum CuisineType {

    GEORGIAN("Khachapuri, Khinkali"),
    ITALIAN("Tagiatele Bolonese, Spaghetti"),
    JAPAN("Sushi, Ramen"),
    AMERICAN("Burgers, fries");

    private final String popularDishes;

    CuisineType(String popularDishes) {
        this.popularDishes = popularDishes;
    }

    public String getPopularDishes() {
        return popularDishes;
    }

}
