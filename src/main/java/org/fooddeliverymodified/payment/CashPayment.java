package org.fooddeliverymodified.payment;

import org.fooddeliverymodified.interfaces.IPay;
import org.fooddeliverymodified.order.Order;

import java.math.BigDecimal;

public class CashPayment extends PaymentMethod {

    public CashPayment(BigDecimal amount) {
        super(amount);
    }

    @Override
    public void process(IPay payment) {
        System.out.println("Payment method cash payment. amount: " + getAmount());
    }

    @Override
    public void makePayment(BigDecimal amount) {
        System.out.println("cash payment. amount: " + getAmount());
    }

    @Override
    public void pay(Order order) {
        System.out.println("Paying order of total $ " + order.calculateTotal());
    }
}
