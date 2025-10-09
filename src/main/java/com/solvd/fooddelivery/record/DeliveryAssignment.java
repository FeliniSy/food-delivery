package com.solvd.fooddelivery.record;

import com.solvd.fooddelivery.deliveryperson.DeliveryPerson;
import com.solvd.fooddelivery.enums.DeliverySpeed;
import com.solvd.fooddelivery.order.Order;

public record DeliveryAssignment(DeliveryPerson deliveryPerson,
                                 Order order,
                                 DeliverySpeed deliverySpeed) {
}
