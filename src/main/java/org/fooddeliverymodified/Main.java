package org.fooddeliverymodified;

import org.fooddeliverymodified.base.FoodDeliveryService;
import org.fooddeliverymodified.delivery.DeliveryLogger;
import org.fooddeliverymodified.deliveryperson.DeliveryPerson;
import org.fooddeliverymodified.enums.CuisineType;
import org.fooddeliverymodified.enums.DeliverySpeed;
import org.fooddeliverymodified.enums.Discount;
import org.fooddeliverymodified.enums.OrderStatus;
import org.fooddeliverymodified.exceptions.RestaurantExcp;
import org.fooddeliverymodified.customer.Customer;
import org.fooddeliverymodified.generics.DeliveryAssignment;
import org.fooddeliverymodified.generics.OrderNode;
import org.fooddeliverymodified.lambdas.DeliveryCalculator;
import org.fooddeliverymodified.lambdas.OrderValidator;
import org.fooddeliverymodified.lambdas.TopCustomerPromo;
import org.fooddeliverymodified.menu.Menu;
import org.fooddeliverymodified.menu.MenuItems;
import org.fooddeliverymodified.order.Order;
import org.fooddeliverymodified.reflectionTest.Test;
import org.fooddeliverymodified.restaurants.Restaurant;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main {

    public Main() {
        super();
    }

    public static void main(String[] args) throws Exception {
        FoodDeliveryService app = new FoodDeliveryService();

        Set<Customer> customerSet = new HashSet<>();

        MenuItems pastry = new MenuItems(CuisineType.GEORGIAN);
        pastry.addMenuItems("khachapuri", BigDecimal.valueOf(20));
        pastry.addMenuItems("khinkali", BigDecimal.valueOf(1.5));

        Menu menu = new Menu();
        menu.addItem(pastry);

        Restaurant restaurant = new Restaurant("Dolce Pizza", "Tsereteli St.", menu);

        try {
            app.addRestaurant(restaurant, 5); // Checked exception
        } catch (RestaurantExcp e) {
            System.out.println("Exception caught: " + e.getMessage());
        } finally {
            System.out.println(restaurant);
        }

        Customer customer1 = new Customer("John Doe", "nikea str");
        Customer customer2 = new Customer("Liza", "nikea str");
        customerSet.add(customer1);
        customerSet.add(customer2);

        Order order = new Order(customer2, restaurant);

        customerSet
                .stream()
                .map(Customer::getName)
                .forEach(System.out::println);

        MenuItems pizza = new MenuItems(CuisineType.ITALIAN);
        pizza.addMenuItems("Pizza", new BigDecimal(15));
        order.addItem(pizza);

        DeliveryPerson dp = new DeliveryPerson("George", "Kutaisi");

        try (DeliveryLogger logger = new DeliveryLogger("delivery_log.txt")) {
            logger.log("New delivery started for customer: " + order.getCustomer().getName());
            order.setStatus(OrderStatus.NEW);
        } catch (IOException e) {
            System.out.println("Logging failed: " + e.getMessage());
        }

        OrderNode<Customer, Order, MenuItems> orderNode = new OrderNode<>(customer1, order);
        orderNode.addItem(pizza);
        System.out.println(orderNode);

        //Record
        DeliveryAssignment<DeliveryPerson, Order> deliveryAssignment = new DeliveryAssignment<>(dp, order, DeliverySpeed.STANDARD);
        System.out.println(deliveryAssignment);

        //the first element from set
        String firstCustomer = customerSet.iterator().next().getName();
        System.out.println(firstCustomer);

        //DeliveryCalculator
        //costumer2 address nikea str, restaurant address tsereteli
        DeliveryCalculator calculator = ((distanceKM, price) -> {
            double baseFee = 2.0;
            double perKM = 0.5;
            return baseFee + distanceKM * perKM + price.doubleValue();
        });

        double distance = 4.3;
        double fee = order.calculate(distance, order.calculateTotal());
        System.out.println("Delivery fee: " + fee);

        //OrderValidator
        OrderValidator validator = order1 -> !order1.getItems().isEmpty() && order1.getCustomer() != null;

        if (validator.validate(order)) {
            System.out.println("order is valid");
        } else {
            System.out.println("order is not valid");
        }

        //TopCustomerPromo
        TopCustomerPromo promo = order1 -> order1.calculateTotal().doubleValue() * Discount.PROMOCODE.getDiscount();

        System.out.println("Total with promo: " + promo.calculateWithPromoCode(order));

        //Built-in lambdas
        Predicate<Order> expensiveOrder = order1 -> order1.calculateTotal().doubleValue() > 50.0;
        Function<Order, String> customerName = order1 -> order1.getCustomer().getName();
        Supplier<Integer> deliveryTime = () -> new Random().nextInt(60);
        Consumer<Order> printOrder = order1 -> System.out.println("Order: " + order1);
        BiFunction<Double, Double, Double> addDeliveryFee = Double::sum;

        if (expensiveOrder.test(order)) {
            System.out.println("Expensive order for: " + customerName.apply(order));
            printOrder.accept(order);
            System.out.println("Total with delivery: " +
                    addDeliveryFee.apply(order.calculateTotal().doubleValue(), 5.0));
            System.out.println("Delivery time: " + deliveryTime.get() + " minutes");
        }

        //---------streams-----------------
        //distinct and foreach
        customerSet
                .stream()
                .map(Customer::getName)
                .distinct()
                .forEach(System.out::println);

        //flatmap
        List<MenuItems> allItems = List.of(order)
                .stream()
                .flatMap(order1 -> order1.getItems().stream())
                .toList();

        //reduce
        BigDecimal total = List.of(order)
                .stream()
                .map(Order::calculateTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("total revenue: " + total);

        //sorted
        List<Order> sortedOrders = List.of(order)
                .stream()
                .sorted(Comparator.comparing(Order::calculateTotal))
                .toList();
        System.out.println("Sorted orders: " + sortedOrders);

        //filter
        List<Customer> filteredName = customerSet
                .stream()
                .filter(n -> n.getName().startsWith("N"))
                .toList();

        //collect
        Set<String> availableDP = DeliveryPerson.getDp()
                .stream()
                .filter(DeliveryPerson::isAvailable)
                .map(DeliveryPerson::getName)
                .collect(Collectors.toSet());

        String joinedNames = customerSet.stream()
                .map(Customer::getName)
                .reduce((a, b) -> a + ", " + b)
                .orElse("No customers");
        System.out.println("All customers: " + joinedNames);

        boolean allAvailable = List.of(dp)
                .stream()
                .allMatch(DeliveryPerson::isAvailable);
        System.out.println("All couriers available? " + allAvailable);

        //Custom Reflection
        Order testOrder = new Order(new Customer("jason", "tbilisi"),
                new Restaurant("kolkha", "tbilisi", new Menu()));

        Class<?> clazz = testOrder.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                Test test = method.getAnnotation(Test.class);
                System.out.println(test.value());
                method.invoke(order);
            }
        }
    }
}
