package org.fooddeliverymodified.base;

import org.fooddeliverymodified.delivery.Delivery;
import org.fooddeliverymodified.deliveryperson.DeliveryPerson;
import org.fooddeliverymodified.exceptions.BusyDeliveryPersonExc;
import org.fooddeliverymodified.exceptions.RestaurantExcp;
import org.fooddeliverymodified.restaurants.Restaurant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FoodDeliveryService {

    private List<Restaurant> restaurants;
    private Set<DeliveryPerson> deliveryPeople;
    private List<Delivery> deliveries;

    public FoodDeliveryService() {
        this.restaurants = new ArrayList<>();
        this.deliveryPeople = new HashSet<>();
        this.deliveries = new ArrayList<>();
    }

    public void addDelivery(Delivery delivery) {
        deliveries.add(delivery);
    }

    public void showAllDeliveries() {
        System.out.println(deliveries.size());
    }

    public void getRestaurants() {
        restaurants.stream().forEach(System.out::println);
    }


    public void getDeliveryPeople() {
        deliveryPeople.stream().forEach(System.out::println);
    }

    public void getDeliveries() {
        deliveries.stream().forEach(System.out::println);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void addRestaurant(Restaurant restaurant, int index) throws RestaurantExcp {
        while (restaurants.size() <= index) {
            restaurants.add(null);
        }
        if (restaurants.get(index) != null) {
            throw new RestaurantExcp("Restaurant slot is already occupiet at index " + index);
        }
        restaurants.set(index, restaurant);
    }

    public void addDeliveryPerson(DeliveryPerson person, int index) {
        if (!person.isAvailable()) {
            throw new BusyDeliveryPersonExc("Delivery person " + person.getName() + " is busy!");
        }
        deliveryPeople.add(person);
    }
}