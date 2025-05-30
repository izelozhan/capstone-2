package Models;

import MenuItems.*;
import Pricing.Product;
import Pricing.SizePrice;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    public ArrayList<Sandwich> sandwiches;
    public ArrayList<Selection<Product>> drinks;
    public ArrayList<Selection<Product>> chips;
    LocalDateTime timeStamp;
    Customer customer;
    Store store;

    public Order(Store store) {
        this.store = store;
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();
        this.timeStamp = LocalDateTime.now();
    }

    public double getTotal() {
        double total = 0;

        for (Sandwich s : this.sandwiches) {
            total += s.getPrice();
        }
        for (Selection<Product> c : this.chips) {
            total += c.getPricing().getPrice();
        }
        for (Selection<Product> c : this.drinks) {
            total += c.getPricing().getPrice();
        }
        return total;
    }

    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // returns order's summary to use in console / writing to file
    public String getSummary() {
        StringBuilder out = new StringBuilder();

        out.append("===== Checkout Summary =====\n");
        out.append("Customer:\t").append(customer.getName()).append("\n");
        out.append("Order Date:\t").append(this.timeStamp.toString()).append("\n");
        out.append("============================\n\n");

        out.append("Sandwiches:\n");
        int sandwichIndex = 1;

        for (Sandwich sandwich : sandwiches) {
            // print signature sandwich name or 'Sandwich 1, Sandwich 2, etc
            String name = "Sandwich " + sandwichIndex;
            if (sandwich instanceof SignatureSandwich) {
                name = ((SignatureSandwich) sandwich).getName();
            }
            out.append("\t- ").append(name).append("\n");

            // get bread name and pricing information from selection
            String breadName = sandwich.getBreadSelection().getProduct().getName();
            SizePrice breadPricing = sandwich.getBreadSelection().getPricing();
            out.append("\t\tBread:\t\t").append(breadName)
                    .append(" (").append(breadPricing.getSize().getName())
                    .append(")\t$").append(String.format("%.2f", breadPricing.getPrice())).append("\n");
            out.append("\t\tToasted:\t").append(sandwich.isToasted() ? "Yes" : "No").append("\n");

            // loop in extras to print
            for (Product extra : sandwich.getExtras()) {
                double extraPrice = extra.getPriceForSize(breadPricing.getSize());
                out.append("\t\tExtra:\t\t").append(extra.getName())
                        .append("\t$").append(String.format("%.2f", extraPrice)).append("\n");
            }

            // Toppings
            out.append("\t\t-- Toppings --\n");
            if (sandwich.getToppingList().isEmpty()) {
                out.append("\t\t\t(None)\n");
            } else {
                for (Topping topping : sandwich.getToppingList()) {
                    double toppingPrice = topping.getPriceForSize(breadPricing.getSize());
                    out.append("\t\t\t- ").append(topping.getName())
                            .append("\t$").append(String.format("%.2f", toppingPrice)).append("\n");
                }
            }

            // Sauces, these are free
            out.append("\t\t-- Sauces --\n");
            if (sandwich.getSauceList().isEmpty()) {
                out.append("\t\t\t(None)\n");
            } else {
                for (Product sauce : sandwich.getSauceList()) {
                    out.append("\t\t\t- ").append(sauce.getName()).append("\n");
                }
            }

            // Side
            out.append("\t\t-- Side --\n");
            if (sandwich.getSide() == null) {
                out.append("\t\t\tNo side selected\n");
            } else {
                out.append("\t\t\t").append(sandwich.getSide().getName()).append("\n");
            }

            out.append("\n");
            sandwichIndex++;
        }

        // Drinks
        out.append("Drinks:\n");
        if (drinks.isEmpty()) {
            out.append("\t(No drinks selected)\n");
        } else {
            for (Selection<Product> drink : drinks) {
                out.append("\t- ")
                        .append(drink.toString())
                        .append("\n");
            }
        }

        // Chips
        out.append("\nChips:\n");
        if (chips.isEmpty()) {
            out.append("\t(No chips selected)\n");
        } else {
            for (Selection<Product> chip : chips) {
                out.append("\t- ")
                        .append(chip.toString())
                        .append("\n");
            }
        }

        // Total
        out.append("\n============================\n");
        out.append("TOTAL:\t\t$").append(String.format("%.2f", getTotal())).append("\n");
        out.append("============================\n");

        return out.toString();
    }

}
