package com.solvd.fooddelivery.base;

import com.solvd.fooddelivery.delivery.Delivery;
import com.solvd.fooddelivery.deliveryperson.DeliveryPerson;
import com.solvd.fooddelivery.exceptions.BusyDeliveryPersonExc;
import com.solvd.fooddelivery.exceptions.RestaurantExcp;
import com.solvd.fooddelivery.lambdas.LambdaUtils;
import com.solvd.fooddelivery.restaurants.Restaurant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FoodDeliveryService {

    private static final Logger logger = LogManager.getLogger(FoodDeliveryService.class);


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
        logger.info(deliveries.size());
    }

    public void getRestaurants() {
        restaurants.forEach(logger::info);
    }


    public void getDeliveryPeople() {
        deliveryPeople.forEach(logger::info);
    }

    public void getDeliveries() {
        deliveries.forEach(logger::info);
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