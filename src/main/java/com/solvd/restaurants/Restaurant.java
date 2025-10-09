package com.solvd.restaurants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.customer.Customer;
import com.solvd.interfaces.IMenu;
import com.solvd.interfaces.Reviewable;
import com.solvd.menu.Menu;
import com.solvd.review.Review;
import java.util.ArrayList;
import java.util.List;

public class Restaurant implements IMenu, Reviewable {

    private static final Logger logger = LogManager.getLogger(Restaurant.class);

    private static int restaurantCount;

    private String restaurantName;
    private String restaurantAddress;
    private Menu menu;
    private List<Review> reviews;

    static {
        restaurantCount = 0;
    }

    public static void getRestaurantCount() {
        logger.info("total number of restaurants: " + restaurantCount);
    }

    public Restaurant(String name, String address, Menu menu) {
        this.restaurantName = name;
        this.restaurantAddress = address;
        this.menu = menu;
        this.reviews = new ArrayList<>();
        restaurantCount++;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    @Override
    public String toString() {
        return "Restaurant{name='" + restaurantName + "', address='" + restaurantAddress + "'}";
    }

    @Override
    public void addReview(Customer customer, String comment) {
        reviews.add(new Review(customer, comment));
    }

    @Override
    public Menu getMenu() {
        return menu;
    }

    public void showReview() {
        for (Review review : reviews) {
            System.out.println(review);
        }
    }
}
