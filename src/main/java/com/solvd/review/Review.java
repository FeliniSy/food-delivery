package com.solvd.review;

import com.solvd.customer.Customer;

public class Review {

    private Customer customer;
    private String comment;

    public Review(Customer customer, String comment) {
        this.customer = customer;
        this.comment = comment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getComment() {
        return comment;
    }

    public String toString() {
        return "Review by " + customer.getName() + ": " + comment;
    }
}