package org.fooddeliverymodified.menu;

import org.fooddeliverymodified.enums.CuisineType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class MenuItems {

    private Map<String, BigDecimal> itemNames;
    private CuisineType cuisineType;

    public MenuItems(CuisineType cuisineType) {
        this.itemNames = new HashMap<>();
        this.cuisineType = cuisineType;
    }

    public void addMenuItems(String itemName, BigDecimal itemPrice) {
        this.itemNames.put(itemName, itemPrice);
    }

    //##
    public BigDecimal getItemPrice() {
        return itemNames.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void display() {
        for (Map.Entry<String, BigDecimal> entry : itemNames.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " " + cuisineType);
        }
    }

    public CuisineType getCuisineType() {
        return cuisineType;
    }

    public String getItemName() {
        return itemNames.values().toString();
    }

    @Override
    public String toString() {
        return itemNames.toString();
    }
}



