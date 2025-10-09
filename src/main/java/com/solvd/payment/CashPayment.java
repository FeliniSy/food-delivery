package com.solvd.payment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.interfaces.IPay;
import com.solvd.order.Order;

import java.math.BigDecimal;

public class CashPayment extends PaymentMethod {

    private static final Logger logger = LogManager.getLogger(CashPayment.class);

    public CashPayment(BigDecimal amount) {
        super(amount);
    }

    @Override
    public void process(IPay payment) {
        logger.info("Payment method cash payment. amount: {} ",getAmount());
    }

    @Override
    public void makePayment(BigDecimal amount) {
        logger.info("cash payment. amount: {} ",getAmount());
    }

    @Override
    public void pay(Order order) {
        logger.info("Paying order of total $ {} ", order.calculateTotal());
    }
}
