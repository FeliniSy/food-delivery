package org.fooddeliverymodified.restaurants;

import org.fooddeliverymodified.customer.Customer;
import org.fooddeliverymodified.interfaces.IMenu;
import org.fooddeliverymodified.interfaces.Reviewable;
import org.fooddeliverymodified.menu.Menu;
import org.fooddeliverymodified.review.Review;
import java.util.ArrayList;
import java.util.List;

public class Restaurant implements IMenu, Reviewable {

    private static int restaurantCount;

    private String restaurantName;
    private String restaurantAddress;
    private Menu menu;
    private List<Review> reviews;

    static {
        restaurantCount = 0;
    }

    public static void getRestaurantCount() {
        System.out.println("total number of restaurants: " + restaurantCount);
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
