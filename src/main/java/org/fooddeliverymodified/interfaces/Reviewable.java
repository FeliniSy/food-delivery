package org.fooddeliverymodified.interfaces;

import org.fooddeliverymodified.customer.Customer;

public interface Reviewable {

    void addReview(Customer customer, String comment);
}
