package com.solvd.fooddelivery.customer;

import com.solvd.fooddelivery.enums.Discount;
import com.solvd.fooddelivery.lambdas.TopCustomerPromo;
import com.solvd.fooddelivery.order.Order;

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
