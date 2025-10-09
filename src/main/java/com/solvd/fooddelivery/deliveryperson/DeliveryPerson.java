package com.solvd.fooddelivery.deliveryperson;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.fooddelivery.interfaces.IDeliver;
import com.solvd.fooddelivery.order.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeliveryPerson implements IDeliver {

    private static final Logger logger = LogManager.getLogger(DeliveryPerson.class.getName());

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
            logger.info("Delivery person {} assigned to deliver order.", name);
        } else {
            logger.info("Delivery person {} Busy.", name);
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

