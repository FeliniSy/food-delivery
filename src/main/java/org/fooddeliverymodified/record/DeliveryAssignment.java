package org.fooddeliverymodified.record;

import org.fooddeliverymodified.deliveryperson.DeliveryPerson;
import org.fooddeliverymodified.enums.DeliverySpeed;
import org.fooddeliverymodified.order.Order;

public record DeliveryAssignment(DeliveryPerson deliveryPerson,
                                 Order order,
                                 DeliverySpeed deliverySpeed) {
}
