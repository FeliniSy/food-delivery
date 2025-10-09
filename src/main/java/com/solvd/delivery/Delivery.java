package com.solvd.delivery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.deliveryperson.DeliveryPerson;
import com.solvd.interfaces.Trackable;
import com.solvd.order.Order;

public class Delivery implements Trackable {

    private static final Logger logger = LogManager.getLogger(Delivery.class.getName());

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
        logger.info("Order is on the way with {} using {}", deliveryPerson.getName(), type);
        if (order.getPayment() == null) {
            logger.warn("⚠ Order not paid yet!");
        }
    }

    @Override
    public void track() {
        logger.info("Tracking delivery for {} by {}", order.getCustomer().getName(), deliveryPerson.getName());
    }
}
