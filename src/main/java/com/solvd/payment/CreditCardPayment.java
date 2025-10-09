package com.solvd.payment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.interfaces.IPay;
import com.solvd.order.Order;

import java.math.BigDecimal;

public class CreditCardPayment extends PaymentMethod {

    private static final Logger logger = LogManager.getLogger(CreditCardPayment.class);

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
        logger.info("Payment method credit card payment. amount" + getAmount());
    }

    @Override
    public void pay(Order order) {
        logger.info("Paying order of total $ " + order.calculateTotal());
    }

    @Override
    public void makePayment(BigDecimal amount) {
        logger.info("credit cart payment. amount: " + getAmount());
    }

}
