package com.solvd.fooddelivery.customer;

import com.solvd.fooddelivery.enums.Discount;

public class Customer {

    private String address;
    private String name;
    private Discount hasDiscount;

    public Customer(String name, String address) {
        this.address = address;
        this.name = name;
        hasDiscount = Discount.NONE;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setHasDiscount(Discount hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public double getHasDiscount(double price) {
        return hasDiscount.getDiscount(price);
    }

    @Override
    public String toString() {
        return "Customer [name=" + name + "]";
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Customer)) return false;
        Customer c = (Customer)o;
        return name.equals(c.name) && address.equals(c.address);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + address.hashCode();
    }
}


