package org.fooddeliverymodified.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private List<MenuItems> menu;

    public Menu() {
        this.menu = new ArrayList<>();
    }

    public void addItem(MenuItems item) {
        menu.add(item);
    }

    public void showMenu() {
        System.out.println("Menu:");
        for(MenuItems item : menu) {
            item.display();
        }
    }

    public List getItems() {
        return menu;
    }
}

