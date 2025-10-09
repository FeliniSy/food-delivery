package com.solvd.record;

import com.solvd.deliveryperson.DeliveryPerson;
import com.solvd.enums.DeliverySpeed;
import com.solvd.order.Order;

public record DeliveryAssignment(DeliveryPerson deliveryPerson,
                                 Order order,
                                 DeliverySpeed deliverySpeed) {
}
