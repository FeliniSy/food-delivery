package org.fooddeliverymodified.delivery;

import org.fooddeliverymodified.deliveryperson.DeliveryPerson;
import org.fooddeliverymodified.interfaces.Trackable;
import org.fooddeliverymodified.order.Order;

public class Delivery implements Trackable {

    private Order order;
    private DeliveryPerson deliveryPerson;
    private DeliveryType type;

    public Delivery(Order order, DeliveryPerson deliveryPerson, DeliveryType type) {
        this.order = order;
        this.deliveryPerson = deliveryPerson;
        this.type = type;
    }

    public DeliveryType getType() {
        return type;
    }

    public DeliveryPerson getDeliveryPerson() {
        return deliveryPerson;
    }

    public Order getOrder() {
        return order;
    }

    public void startDelivery() {
        System.out.println("Order is on the way with " + deliveryPerson.getName() + " using " + type);
        if (order.getPayment() == null) {
            System.out.println("⚠ Order not paid yet!");
        }
    }

    @Override
    public void track() {
        System.out.println("Tracking delivery for " + order.getCustomer().getName() + " by " + deliveryPerson.getName());
    }
}
