package com.solvd.fooddelivery.menu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private static final Logger logger = LogManager.getLogger(Menu.class.getName());

    private List<MenuItems> menu;

    public Menu() {
        this.menu = new ArrayList<>();
    }

    public void addItem(MenuItems item) {
        menu.add(item);
    }

    public void showMenu() {
        logger.info("Menu");
        for(MenuItems item : menu) {
            item.display();
        }
    }

    public List getItems() {
        return menu;
    }
}

