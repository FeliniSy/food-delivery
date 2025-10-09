package com.solvd.fooddelivery.generics;

import com.solvd.fooddelivery.enums.DeliverySpeed;

public class DeliveryAssignment<TD, TOr> {

    private TD deliveryPerson;
    private TOr order;

    public DeliveryAssignment(TD deliveryPerson, TOr order, DeliverySpeed standard) {
        this.deliveryPerson = deliveryPerson;
        this.order = order;
    }

    public TD getDeliveryPerson() {
        return deliveryPerson;
    }

    public TOr getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "DeliveryAssignment{" +
                "deliveryPerson=" + deliveryPerson +
                ", order=" + order +
                '}';
    }
}
