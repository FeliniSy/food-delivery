package org.fooddeliverymodified.customer;

import org.fooddeliverymodified.enums.Discount;
import org.fooddeliverymodified.lambdas.TopCustomerPromo;
import org.fooddeliverymodified.order.Order;


public final class TopCustomer extends Customer implements TopCustomerPromo {

    private final String code;

    public TopCustomer(String name, String address, String code) {
        super(name, address);
        this.code = code;
    }

    public String getCode() {
        return code;
    }


    @Override
    public double calculateWithPromoCode(Order order) {
        TopCustomerPromo topCustomer = order1 -> Discount.PROMOCODE.getDiscount(order1.calculateTotal().doubleValue());
        return topCustomer.calculateWithPromoCode(order);
    }
}
