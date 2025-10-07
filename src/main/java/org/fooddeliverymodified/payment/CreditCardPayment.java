package org.fooddeliverymodified.payment;

import org.fooddeliverymodified.interfaces.IPay;
import org.fooddeliverymodified.order.Order;

import java.math.BigDecimal;

public class CreditCardPayment extends PaymentMethod {

    private String cardNumber;

    public CreditCardPayment(BigDecimal amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void process(IPay payment) {
        System.out.println("Payment method credit card payment. amount" + getAmount());
    }

    @Override
    public void pay(Order order) {
        System.out.println("Paying order of total $ " + order.calculateTotal());
    }

    @Override
    public void makePayment(BigDecimal amount) {
        System.out.println("credit cart payment. amount: " + getAmount());
    }

}
