package org.fooddeliverymodified.payment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fooddeliverymodified.enums.PaymentType;
import org.fooddeliverymodified.interfaces.IPay;
import org.fooddeliverymodified.order.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class PaymentMethod implements IPay {

    private static final Logger logger = LogManager.getLogger(PaymentMethod.class);

    private final BigDecimal amount;
    private LocalDateTime paymentTime;
    private PaymentType type;

    public PaymentMethod(BigDecimal amount) {
        this.amount = amount;
        this.paymentTime = LocalDateTime.now();
        this.type = null;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public abstract void process(IPay payment);

    public abstract void pay(Order order);

    @Override
    public abstract void makePayment(BigDecimal amount);

    //unchangeable
    public final void printPaymentInfo() {
        logger.info("Payment amount: " + amount);
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public PaymentType getType() {
        return type;
    }
}
