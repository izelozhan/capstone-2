package Screens;

import MenuItems.Selection;
import Models.Customer;
import Models.Order;
import Models.Receipt;
import Models.Store;
import Pricing.Product;
import Pricing.SizePrice;
import Utilities.Utils;

import java.io.IOException;

public class OrderScreen {
    Order order;
    Store store;
    public OrderScreen(Order order, Store store) {
        this.order = order;
        this.store = store;
    }

    public void displayWelcomeScreen() throws IOException {
        Utils.printTitle("\n=== Let's build your perfect sandwich! ===");
        order.setCustomer(new Customer(Utils.getStringFromTerminal("Before we start, what's your name?")));
        System.out.println("Nice to meet you, " + order.getCustomer().getName() + "! Let's get started on your order.");
        displayOrderScreen();
    }

    public void displayOrderScreen() throws IOException {
        boolean userCanceled = false;

        while (!userCanceled) {
            Utils.printTitle("\n=== Order Menu ===");
            System.out.println("""
                    1) Add Sandwich
                    2) Add Drink
                    3) Add Chips
                    4) Checkout
                    0) Cancel Order
                    """);

            int userChoice = Utils.getIntegerFromTerminal("Please choose an option (0 to cancel, 1-4 to proceed): ", true);

            switch (userChoice) {
                case 1 -> displaySandwichScreen();
                case 2 -> displayDrinkScreen();
                case 3 -> displayChipScreen();
                case 4 -> {
                    displayCheckoutScreen();
                    userCanceled = true;
                }
                case 0 -> {
                    userCanceled = true;
                    Utils.printExitMessage("\nOrder canceled. Thanks for visiting!");
                }
                default -> Utils.printError("Please choose a valid option!");
            }
        }
    }

    public void displaySandwichScreen() {
        boolean userCanceled = false;
        boolean isSignature = false;

        //get a valid sandwich option
        while (!userCanceled) {
            Utils.printTitle("\nDo you want to build your own sandwich or want to try our Signature Sandwiches?");
            Utils.printMenuOption("1) Create Sandwich!");
            Utils.printMenuOption("2) Signature Sandwiches");
            Utils.printMenuOption("0) Go Back to Order Menu");

            int sandwichChoice = Utils.getIntegerFromTerminal("Please choose an option (0 to go back, 1-2 to proceed): ", true);

            if (sandwichChoice == 1) {
                userCanceled = true;
            } else if (sandwichChoice == 2) {
                isSignature = true;
                userCanceled = true;
            } else if (sandwichChoice == 0) {
                return;
            } else {
                Utils.printError("Please choose a valid options!");
            }
        }

        SandwichScreen screen = new SandwichScreen(order, store, isSignature);
        screen.display();
    }

    public void displayDrinkScreen() {
        //drink flavor
        Utils.printOrderSubtitles("\nDrink flavors: ");
        int drinkIndex = 1;
        for (Product drink : store.drinks.values()) {
            System.out.println(drinkIndex++ + ") " + drink.getName());
        }
        int selectedDrinkIndex = Utils.getIntegerWithRange("Please select flavor: ", true, 1, store.drinks.size());
        Product selectedDrink = store.drinks.values().toArray(Product[]::new)[selectedDrinkIndex - 1];

        //drink size
        Utils.printOrderSubtitles("Please choose a size for your drink: ");
        int drinkSizeIndex = 1;
        for (SizePrice sp : selectedDrink.getSizePrices()) {
            System.out.println(drinkSizeIndex++ + ")" + sp.getSize().getName() + " - $" + sp.getPrice());
        }
        int selectedSizeIndex = Utils.getIntegerWithRange("Enter a number: ", true, 1, selectedDrink.getSizePrices().length);
        SizePrice drinkSize = selectedDrink.getSizePrices()[selectedSizeIndex - 1];

        order.drinks.add(new Selection<>(selectedDrink, drinkSize));

        System.out.println("\nThank you! We added your drink to your order.");
        System.out.println("Redirecting you to the order menu...");

        // 3 seconds
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void displayChipScreen() {
        Utils.printOrderSubtitles("\nChip Flavors: ");
        int chipIndex = 1;
        for (Product chip : store.chips.values()) {
            System.out.println(chipIndex++ + ") " + chip.getName());
        }
        int selectedChipIndex = Utils.getIntegerWithRange("Enter a number: ", true, 1, store.chips.size());

        Product selectedChip = store.chips.values().toArray(Product[]::new)[selectedChipIndex - 1];

        Utils.printGetUserOption("Please choose a size: ");
        int chipSizeIndex = 1;
        for (SizePrice sp : selectedChip.getSizePrices()) {
            System.out.println(chipSizeIndex++ + ")" + sp.getSize().getName() + " - $" + sp.getPrice());
        }
        int selectedChipSizeIndex = Utils.getIntegerWithRange("Enter a number: ", true, 1, selectedChip.getSizePrices().length);
        SizePrice chipSize = selectedChip.getSizePrices()[selectedChipSizeIndex - 1];
        order.chips.add(new Selection<>(selectedChip, chipSize));

        System.out.println("\nThank you! We added your chip to your order.");
        System.out.println("Redirecting you to the order menu...");

        // 3 seconds
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void displayCheckoutScreen() throws IOException {
        System.out.println(order.getSummary());
        boolean confirmOrder = Utils.getStringFromTerminal("Confirm order? [Y]es / [N]o").equalsIgnoreCase("y");
        if (confirmOrder) {
            Receipt.writeToFile(order);
        }
    }
}
