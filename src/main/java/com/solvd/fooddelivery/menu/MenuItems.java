package com.solvd.fooddelivery.menu;

import com.solvd.fooddelivery.enums.CuisineType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class MenuItems {

    private static final Logger logger = LogManager.getLogger(MenuItems.class);

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
            logger.info("{}: {} {}", entry.getKey(), entry.getValue(), cuisineType);
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



