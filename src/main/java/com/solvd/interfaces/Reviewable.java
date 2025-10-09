package com.solvd.interfaces;

import com.solvd.customer.Customer;

public interface Reviewable {

    void addReview(Customer customer, String comment);
}
