import Enums.BreadType;
import MenuItems.*;
import Pricing.Product;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    ArrayList<Sandwich> sandwiches;
    ArrayList<Selection<Product>> drinks;
    ArrayList<Selection<Product>> chips;
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
            total += c.getProduct().getPriceForSize(c.getPricing().getSize());
        }
        for (Selection<Product> c : this.drinks) {
            total += c.getProduct().getPriceForSize(c.getPricing().getSize());
        }

        return total;
    }

    public String getSummary() throws IOException {
        StringBuilder out = new StringBuilder();

        out.append("===== Checkout Summary =====\n");
        out.append("Customer     : " + customer.getName()).append("\n");
        out.append("Order date   : " + this.timeStamp.toString());
        out.append("\n============================\n\n");

        out.append("Sandwiches:\n");
        int sandwichIndex = 1;

        for (Sandwich sandwich : sandwiches) {
            String name = "Sandwich " + sandwichIndex;
            if (sandwich instanceof SignatureSandwich) {
                name = ((SignatureSandwich) sandwich).getName();
            }

            out.append("  - ").append(name).append("\n");
            out.append("    Bread Type     : ").append(sandwich.getBreadSelection().getProduct().getName()).append("\n");
            out.append("    Toasted        : ").append(sandwich.isToasted() ? "Yes" : "No").append("\n");
            for(Product extra : sandwich.getExtras()) {
                out.append("    " + extra.getName() + "     : Yes\n");
            }

            out.append("    -- Toppings --\n");
            if (sandwich.getToppingList().isEmpty()) {
                out.append("      (None)\n");
            } else {
                for (Topping topping : sandwich.getToppingList()) {
                    out.append("      - ").append(topping.getName()).append("\n");
                }
            }

            out.append("    -- Sauces --\n");
            if (sandwich.getSauceList().isEmpty()) {
                out.append("      (None)\n");
            } else {
                for (Product sauce : sandwich.getSauceList()) {
                    out.append("      - ").append(sauce.getName()).append("\n");
                }
            }

            out.append("    -- Side --\n");
            if (sandwich.getSide() == null) {
                out.append("      No side selected\n");
            } else {
                out.append("      ").append(sandwich.getSide().getName()).append("\n");
            }

            out.append("\n");
            sandwichIndex++;
        }

        out.append("Drinks:\n");

        if (drinks.isEmpty()) {
            out.append("  (No drinks selected)\n");
        } else {
            for (Selection<Product> drink : drinks) {
                out.append("  - ").append(drink.getProduct().getName())
                        .append(" (").append(drink.getPricing().getSize().getName()).append(")\n");
            }
        }

        out.append("\nChips:\n");
        if (chips.isEmpty()) {
            out.append("  (No chips selected)\n");
        } else {
            for (Selection<Product> chip : chips) {
                out.append("  - ").append(chip.getProduct().getName())
                        .append(" (").append(chip.getPricing().getSize().getName()).append(")\n");
            }
        }

        out.append("\n============================\n");
        out.append("TOTAL: ").append(getTotal()).append("\n");
        out.append("============================\n");

        return out.toString();
    }
}
