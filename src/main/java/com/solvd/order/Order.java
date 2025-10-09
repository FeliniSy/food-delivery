package com.solvd.order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.enums.OrderStatus;
import com.solvd.exceptions.InvalidPaymentAmountException;
import com.solvd.exceptions.OrderAlreadyDeliveredException;
import com.solvd.interfaces.IPay;
import com.solvd.lambdas.DeliveryCalculator;
import com.solvd.lambdas.OrderValidator;
import com.solvd.payment.PaymentMethod;
import com.solvd.customer.Customer;

import com.solvd.menu.MenuItems;
import com.solvd.reflectionTest.Test;
import com.solvd.restaurants.Restaurant;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order implements DeliveryCalculator, OrderValidator {

    private static final Logger logger = LogManager.getLogger(Order.class.getName());

    private Customer customer;
    private Restaurant restaurant;
    private boolean delivered;
    private List<MenuItems> items;
    private LocalDateTime deliverTime;
    private PaymentMethod payment;
    private OrderStatus status;

    public Order(Customer customer, Restaurant restaurant) {
        this.customer = customer;
        this.restaurant = restaurant;
        this.items = new ArrayList<>();
        this.delivered = false;
        this.deliverTime = LocalDateTime.now();
        this.payment = null;
        this.status = null;
    }

    public void addItem(MenuItems item) {
        items.add(item);
    }

    public void payOrder(BigDecimal paidAmount) {
        if (paidAmount.compareTo(calculateTotal()) < 0) {
            throw new InvalidPaymentAmountException("Paid amount is less than total!");
        }
        if (delivered) {
            throw new OrderAlreadyDeliveredException("Order is already delivered!");
        }
        delivered = true;
    }

    public void processPayment(IPay payment) {
        payment.makePayment(this.calculateTotal());
    }

    public BigDecimal calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (MenuItems item : items) {
            total = total.add(item.getItemPrice());
        }
        return total;
    }

    public BigDecimal getChange(BigDecimal paidAmount) {
        if (payment == null) {
            logger.info("order not paid yet!");
            return BigDecimal.ZERO;
        }
        return paidAmount.subtract(payment.getAmount());
    }

    public void display() {
        for (MenuItems item : items) {
            System.out.println(item);
        }
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public double calculate(double distanceKM, BigDecimal price) {
        DeliveryCalculator deliveryCalculator = (dist, p) ->{
            if (p.compareTo(calculateTotal()) < 0) {
                return 0;
            }
            return distanceKM * p.doubleValue() * 0.02;
        };
        return deliveryCalculator.calculate(distanceKM, price);
    }

    @Override
    public boolean validate(Order order) {
     OrderValidator status = order1 -> order1.calculateTotal().compareTo(BigDecimal.valueOf(100)) > 0;
     return status.validate(order);
    }

    public PaymentMethod getPayment() {
        return payment;
    }

    public LocalDateTime getOrderTime() {
        return deliverTime;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDateTime getDeliverTime() {
        return deliverTime;
    }

    public List<MenuItems> getItems() {
        return items;
    }

    public boolean isDelivered() {
        return delivered;
    }

    @Test("tester")
    public void printii(){
        logger.info("test print {} ",calculateTotal());
    }
}
