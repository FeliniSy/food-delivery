package com.solvd.fooddelivery.order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.fooddelivery.enums.OrderStatus;
import com.solvd.fooddelivery.exceptions.InvalidPaymentAmountException;
import com.solvd.fooddelivery.exceptions.OrderAlreadyDeliveredException;
import com.solvd.fooddelivery.interfaces.IPay;
import com.solvd.fooddelivery.lambdas.DeliveryCalculator;
import com.solvd.fooddelivery.lambdas.OrderValidator;
import com.solvd.fooddelivery.payment.PaymentMethod;
import com.solvd.fooddelivery.customer.Customer;

import com.solvd.fooddelivery.menu.MenuItems;
import com.solvd.fooddelivery.reflectionTest.Test;
import com.solvd.fooddelivery.restaurants.Restaurant;

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
            logger.info(item);
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
