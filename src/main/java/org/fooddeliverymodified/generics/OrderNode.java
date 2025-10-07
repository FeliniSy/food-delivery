package org.fooddeliverymodified.generics;

import java.util.ArrayList;
import java.util.List;

public class OrderNode<C, O, M> {

    private C customer;
    private O order;
    private List<M> items;

    public OrderNode(C customer, O order) {
        this.customer = customer;
        this.order = order;
        this.items = new ArrayList<>();
    }

    public void addItem(M item) {
        items.add(item);
    }

    public C getCustomer() {
        return customer;
    }

    public O getOrder() {
        return order;
    }

    public List<M> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "OrderNode{" +
                "customer=" + customer +
                ", order=" + order +
                ", items=" + items +
                '}';
    }
}

