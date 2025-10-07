package org.fooddeliverymodified.deliveryperson;

import org.fooddeliverymodified.interfaces.IDeliver;
import org.fooddeliverymodified.order.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeliveryPerson implements IDeliver {

    private static List<DeliveryPerson> dp = new ArrayList<>();

    private String name;
    private boolean available;
    private String address;
    private Order currentOrder;

    public DeliveryPerson(String name, String address) {
        this.name = name;
        this.available = true;
        this.address = address;
        dp.add(this);
    }

    @Override
    public void assignOrder(Order order) {
        if (available) {
            currentOrder = order;
            available = false;
            System.out.println("Delivery person " + name + " assigned to deliver order.");
        } else {
            System.out.println("Delivery person " + name + " is busy.");
        }
    }

    public static List<DeliveryPerson> getDp() {
        return dp;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getAddress() {
        return address;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    @Override
    public String toString() {
        return "DeliveryPerson{name='" + name + "', " + "', available=" + available + "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DeliveryPerson)) return false;
        DeliveryPerson that = (DeliveryPerson) obj;
        return Objects.equals(name, that.name) &&
                Objects.equals(address, that.address);
    }
}

