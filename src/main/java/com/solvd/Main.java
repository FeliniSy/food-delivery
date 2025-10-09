package com.solvd;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.base.FoodDeliveryService;
import com.solvd.customer.Customer;
import com.solvd.delivery.DeliveryLogger;
import com.solvd.deliveryperson.DeliveryPerson;
import com.solvd.enums.CuisineType;
import com.solvd.enums.DeliverySpeed;
import com.solvd.enums.Discount;
import com.solvd.enums.OrderStatus;
import com.solvd.exceptions.RestaurantExcp;
import com.solvd.generics.OrderNode;
import com.solvd.lambdas.DeliveryCalculator;
import com.solvd.lambdas.LambdaUtils;
import com.solvd.lambdas.OrderValidator;
import com.solvd.lambdas.TopCustomerPromo;
import com.solvd.menu.Menu;
import com.solvd.menu.MenuItems;
import com.solvd.order.Order;
import com.solvd.record.DeliveryAssignment;
import com.solvd.reflectionTest.Test;
import com.solvd.restaurants.Restaurant;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void checkOrder(Order order, OrderValidator validator) {
        if (validator.validate(order)) {
            logger.info("Order is valid");
        } else {
            logger.info("Order is not valid");
        }
    }
    
    private static final Logger logger = LogManager.getLogger(Main.class);

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
            logger.warn("Exception caught: " + e.getMessage());
        } finally {
            logger.info(restaurant);
        }

        Customer customer1 = new Customer("John Doe", "nikea str");
        Customer customer2 = new Customer("Liza", "tsereteli str");
        customerSet.add(customer1);
        customerSet.add(customer2);

        Order order1 = new Order(customer2, restaurant);

        customerSet.forEach(customer -> logger.info(customer.getName()));
        customerSet.forEach(System.out::println);

        MenuItems pizza = new MenuItems(CuisineType.ITALIAN);
        pizza.addMenuItems("Pizza", new BigDecimal(15));
        order1.addItem(pizza);

        DeliveryPerson dp = new DeliveryPerson("Giorgi", "Kutaisi");

        try (DeliveryLogger logger = new DeliveryLogger("delivery_log.txt")) {
            logger.log("New delivery started for customer: " + order1.getCustomer().getName());
            order1.setStatus(OrderStatus.NEW);
        } catch (IOException e) {
            logger.info("Logging failed: " + e.getMessage());
        }

        OrderNode<Customer, Order, MenuItems> orderNode = new OrderNode<>(customer1, order1);
        orderNode.addItem(pizza);
        logger.info(orderNode);

        //Record
        DeliveryAssignment da = new DeliveryAssignment(dp, order1, DeliverySpeed.STANDARD);
        logger.info("Delivery assignment for customer: " + order1.getCustomer().getName());

        //the first element from set
        String firstCustomer = customerSet.iterator()
                .next().
                getName();
        logger.info(firstCustomer);

        //DeliveryCalculator
        //costumer2 address nikea str, restaurant address tsereteli
        DeliveryCalculator calculator = ((distanceKM, price) -> {
            double baseFee = 2.0;
            double perKM = 0.5;
            return baseFee + distanceKM * perKM + price.doubleValue();
        });

        double distance = 4.3;
        double fee = order1.calculate(distance, order1.calculateTotal());
        logger.info("Delivery fee: " + fee);

        //OrderValidator
        OrderValidator validator = order ->
                !order.getItems().isEmpty()
                        && order.getCustomer() != null;

        if (validator.validate(order1)) {
            logger.info("order is valid");
        } else {
            logger.info("order is not valid");
        }

        //TopCustomerPromo
        TopCustomerPromo promo = order ->
                order.calculateTotal().doubleValue() * Discount.PROMOCODE.getDiscount();

        logger.info("Total with promo: " + promo.calculateWithPromoCode(order1));

        //lambda Utils
        logger.info("---------------------------");
        if (LambdaUtils.EXPENSIVE_ORDER.test(order1)) {
            logger.info("Expensive order");
        }
        logger.info("Customer " + LambdaUtils.CUSTOMER_NAME.apply(order1));

        logger.info("Delivery time " + LambdaUtils.DELIVERY_TIME.get());

        LambdaUtils.PRINT_ORDER.accept(order1);

        double totalFee = LambdaUtils.ADD_DELIVERY_FEE.apply(10.0, 5.0);
        logger.info("Total fee: " + totalFee);
        logger.info("----------------------");

        //---------streams-----------------
        //distinct and foreach
        customerSet.stream()
                .map(Customer::getName)
                .distinct()
                .forEach(System.out::println);

        //flatmap
        List<MenuItems> allItems = List.of(order1).stream()
                .flatMap(order -> order.getItems().stream())
                .toList();

        //reduce
        BigDecimal total = List.of(order1).stream()
                .map(Order::calculateTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        logger.info("total revenue: " + total);

        //sorted
        List<Order> sortedOrders = List.of(order1).stream()
                .sorted(Comparator.comparing(Order::calculateTotal))
                .toList();
        logger.info("Sorted orders: " + sortedOrders);

        //filter
        List<Customer> filteredName = customerSet.stream()
                .filter(n -> n.getName().startsWith("N"))
                .toList();

        //collect
        Set<String> availableDP = DeliveryPerson.getDp().stream()
                .filter(DeliveryPerson::isAvailable)
                .map(DeliveryPerson::getName)
                .collect(Collectors.toSet());

        String joinedNames = customerSet.stream().map(Customer::getName)
                .reduce((a, b) -> a + ", " + b)
                .orElse("No customers");
        logger.info("All customers: " + joinedNames);

        boolean allAvailable = List.of(dp).stream()
                .allMatch(DeliveryPerson::isAvailable);
        logger.info("All couriers available? " + allAvailable);

        //Custom Reflection
        Order testOrder = new Order(new Customer("jason", "tbilisi"),
                new Restaurant("kolkha", "tbilisi", new Menu()));

        Class<?> clazz = testOrder.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                Test test = method.getAnnotation(Test.class);
                logger.info(test.value());
                method.invoke(order1);
            }
        }
        checkOrder(order1, order -> !order.getItems().isEmpty() && order.getCustomer() == null);
    }
}
