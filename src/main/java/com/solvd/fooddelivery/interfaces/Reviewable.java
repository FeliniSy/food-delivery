package com.solvd.fooddelivery.interfaces;

import com.solvd.fooddelivery.customer.Customer;

public interface Reviewable {

    void addReview(Customer customer, String comment);
}
