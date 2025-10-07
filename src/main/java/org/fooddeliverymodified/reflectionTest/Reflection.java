package org.fooddeliverymodified.reflectionTest;

import org.fooddeliverymodified.order.Order;
import org.fooddeliverymodified.customer.Customer;
import org.fooddeliverymodified.restaurants.Restaurant;
import org.fooddeliverymodified.menu.Menu;

import java.lang.reflect.*;
import java.math.BigDecimal;

public class Reflection {

    public static void main(String[] args) throws Exception {
        Class<Order> clazz = Order.class;

        for (Field f : clazz.getDeclaredFields()) {
            System.out.println("Field: " + f.getName() + ", Type: " + f.getType());
        }

        for (Constructor<?> c : clazz.getConstructors()) {
            System.out.println("Constructor: " + c.getName() + ", params: " + c.getParameterCount());
        }

        for (Method m : clazz.getDeclaredMethods()) {
            System.out.println("Method: " + m.getName() + ", return type: " + m.getReturnType());
        }

        Constructor<Order> cons = clazz.getConstructor(Customer.class, Restaurant.class);
        Order reflectedOrder = cons.newInstance(
                new Customer("Alice", "Tbilisi"),
                new Restaurant("Pizza Hut", "Rustaveli", new Menu())
        );

        Method calcTotal = clazz.getDeclaredMethod("calculateTotal");
        calcTotal.setAccessible(true);
        BigDecimal totalPrice = (BigDecimal) calcTotal.invoke(reflectedOrder);
        System.out.println("Reflected total price: " + totalPrice);
    }
}
