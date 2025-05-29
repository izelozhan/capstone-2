import Enums.BreadType;
import MenuItems.*;
import Pricing.Size;
import Pricing.SizePrice;
import Utilities.Utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Order {
    ArrayList<Sandwich> sandwiches;
    ArrayList<Selection<Drink>> drinks;
    ArrayList<Selection<Chip>> chips;

    LocalDateTime timeStamp;
    Customer customer;
    Store store;
    ArrayList<SignatureSandwich> signatureSandwiches;

    public Order(Store store) {
        this.store = store;
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();
        this.timeStamp = LocalDateTime.now();
        this.signatureSandwiches = store.generateSignatureSandwiches();
    }

    public void init() throws IOException {
        Utils.printTitle("\n=== Welcome to the Delicious Order Screen! Let's build your perfect sandwich! ===");
        this.customer = new Customer(Utils.getStringFromTerminal("Before we start, what's your name?"));
        System.out.println("Nice to meet you, " + customer.getName() + "! Let's get started on your order.");
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
                isSignature = false;
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
        displayCreateSandwichScreen(isSignature);
    }

    public void displayCreateSandwichScreen(boolean isSignature) {
        Sandwich sandwich = null;
        boolean addTopping = true;
        boolean addSauce = true;
        boolean addSide = true;

        //SANDWICH BASE
        if (!isSignature) {
            sandwich = new Sandwich();

            //SIZE
            Utils.printOrderSubtitles("\nAvailable Sizes:");
            for (int i = 1; i <= store.availableSizes.length; i++) {
                System.out.println(i + ")" + store.availableSizes[i - 1].getName());
            }
            int sizeIndex = Utils.getIntegerWithRange("Please select size: ", true, 1, store.availableSizes.length);
            Size selectedSize = store.availableSizes[sizeIndex - 1];

            //BREAD
            Utils.printOrderSubtitles("\nPlease select your bread:");
            int breadIndex = 1;
            for (BreadType type : store.breads.keySet()) {
                System.out.println(breadIndex++ + ")" + type.name());
            }
            breadIndex = Utils.getIntegerWithRange("Please select bread: ", true, 1, store.breads.size());

            //converting keySet stream to Array
            BreadType breadType = store.breads.keySet().toArray(BreadType[]::new)[breadIndex - 1];
            Bread finalBread = store.breads.get(breadType);
            SizePrice selectedBreadPrice = Arrays.stream(finalBread.getSizePrices()).filter(i -> i.getSize().equals(selectedSize)).findFirst().get();

            //Set size and bread
            sandwich.setBreadSelection(new Selection<Bread>(finalBread, selectedBreadPrice));


        } else {
            //Signature Sandwich
            Utils.printTitle("\nOur Signature Sandwiches:");

            for (int i = 1; i <= signatureSandwiches.size(); i++) {
                SignatureSandwich option = signatureSandwiches.get(i - 1);
                System.out.println(i + ") " + option.getName() + " - $" + option.getPrice());
            }

            int signatureChoice = Utils.getIntegerWithRange("Please select sandwich: ", true, 1, signatureSandwiches.size());
            sandwich = signatureSandwiches.get(signatureChoice - 1);

            // list toppings/ sauce and toasted info for signature
            if (!sandwich.getToppingList().isEmpty()) {
                Utils.printUnderline("\nThis sandwich comes with following toppings: ");
                String joined = String.join(" | ", sandwich.getToppingList().stream().map(i -> i.getName()).toArray(String[]::new));
                System.out.println(joined);
            }
            if (!sandwich.getSauceList().isEmpty()) {
                Utils.printUnderline("\nThis sandwich comes with following sauces: ");
                String joined = String.join(" | ", sandwich.getSauceList().stream().map(i -> i.getName()).toArray(String[]::new));
                System.out.println(joined);
//                int size = sandwich.getSauceList().size();
//                for (int i = 0; i < size; i++) {
//                    Sauce sauce = sandwich.getSauceList().get(i);
//                    System.out.print(sauce.getName());
//                    if (i < size - 1) {
//                        System.out.print(" | ");
//                    }
//                }
            }
            if (sandwich.isToasted()) {
                System.out.println("\nThis sandwich is toasted!");
            }

        }
        //ask user to add extra toppings to signature sandwich
        if (isSignature) {
            Utils.printOrderSubtitles("\nDo you want to add more toppings? ");
            Utils.printMenuOption("1) Yes I would like to add more toppings! ");
            Utils.printMenuOption("2) No, I'm good!");
            int input = Utils.getIntegerWithRange("Please choose an option: ", true, 1, 2);
            switch (input) {
                case 1 -> {
                }
                case 2 -> addTopping = false;
            }
        }

        //ADD TOPPINGS
        while (addTopping) {
            Utils.printOrderSubtitles("\nSelect any toppings you would like to add: ");

            int toppingIndex = 1; // starts with 1 because I want to list items

            for (Topping topping : store.toppings.values()) {
                double toppingPrice = topping.getPriceForSize(sandwich.getBreadSelection().getPricing().getSize());
                boolean exist = sandwich.getToppingList().contains(topping);

                String priceText = toppingPrice == 0 ? " - FREE" : " - $" + toppingPrice;
                String printTopping = toppingIndex++ + ") " + topping.getName() + priceText;

                if (!exist) {
                    Utils.printMenuOption(printTopping);
                } else {
                    Utils.printExistTopping(printTopping);
                }
            }
            Utils.printMenuOption("0) Skip");

            int userTopping = Utils.getIntegerWithRange("Please select topping: ", true, 0, store.toppings.size());

            if (userTopping != 0) {
                // converts a new topping array and collect selected toppings, (-1 because index starts with 1)
                Topping selectedTopping = store.toppings.values().toArray(Topping[]::new)[userTopping - 1];
                sandwich.addTopping(selectedTopping);
            } else {
                addTopping = false;
            }
        }

        if (!sandwich.getToppingList().isEmpty()) {
            List<Topping> existingToppings = sandwich.getToppingList();
            boolean hasMeat = existingToppings.stream().anyMatch(i -> i.isPremium() && i.isMeat());
            boolean hasCheese = existingToppings.stream().anyMatch(i -> i.isPremium() && i.isCheese());

            if (hasMeat) {
                Utils.printOrderSubtitles("\nYou have premium meat. Would you like to add extra meat?");
                Utils.printMenuOption("1) Yes!");
                Utils.printMenuOption("2) No.");

                int input = Utils.getIntegerWithRange("Please choose an option: ", true, 1, 2);
                switch (input) {
                    case 1 -> sandwich.setHasExtraMeat(true);
                    case 2 -> sandwich.setHasExtraMeat(false);
                }
            }

            if (hasCheese) {
                Utils.printOrderSubtitles("\nYou have premium cheese. Would you like to add extra cheese?");
                Utils.printMenuOption("1) Yes!");
                Utils.printMenuOption("2) No.");

                int input = Utils.getIntegerWithRange("Please choose an option: ", true, 1, 2);
                switch (input) {
                    case 1 -> sandwich.setHasExtraCheese(true);
                    case 2 -> sandwich.setHasExtraCheese(false);
                }
            }
        }

        //ADD SAUCE
        while (addSauce) {
            Utils.printOrderSubtitles("Select select any sauces you would like to add: ");
            int sauceIndex = 1;
            for (Sauce sauce : store.sauces.values()) {
                boolean exist = sandwich.getSauceList().contains(sauce);
                String printSauce = sauceIndex++ + ") " + sauce.getName() + " - FREE";

                if (!exist) {
                    Utils.printMenuOption(printSauce);
                } else {
                    Utils.printExistTopping(printSauce);
                }
            }
            Utils.printMenuOption("0) Skip");

            int userSauce = Utils.getIntegerWithRange("Please enter sauce: ", true, 0, store.sauces.size());
            if (userSauce == 0) {
                addSauce = false;
            } else {
                Sauce selectedSauce = store.sauces.values().toArray(Sauce[]::new)[userSauce - 1];
                sandwich.addSauce(selectedSauce);
            }
        }

        //IS TOASTED
        if (sandwich.isToasted()) {
            Utils.printOrderSubtitles("\nThis sandwich is toasted, but we can do not toasted, do you want it toasted?");
            Utils.printMenuOption("1) Yes I would like to continue with toasted! ");
            Utils.printMenuOption("2) No, I would like to not toasted!");
        }

        Utils.printOrderSubtitles("\nWould you like the sandwich toasted?");
        Utils.printMenuOption("1) Yes I would like to! ");
        Utils.printMenuOption("2) No, I'm good!");

        int input = Utils.getIntegerWithRange("Please choose an option: ", true, 1, 2);
        switch (input) {
            case 1 -> sandwich.setToasted(true);
            case 2 -> sandwich.setToasted(false);
        }

        //SIDES
        while (addSide) {
            System.out.println("Select side you would like to add: ");
            int sideIndex = 1;
            for (Side side : store.sides.values()) {
                System.out.println(sideIndex++ + ") " + side.getName());
            }
            Utils.printMenuOption("0) Skip");

            int userSide = Utils.getIntegerWithRange("Please enter side number: ", true, 0, store.sides.size());
            if (userSide == 0) {
                addSide = false;
            } else {
                Side selectedSide = store.sides.values().toArray(Side[]::new)[userSide - 1];
                sandwich.setSide(selectedSide);
                addSide = false; // only one side
            }
        }
        System.out.println("Thank you! We added your sandwich to your order.");
        this.sandwiches.add(sandwich);

        System.out.println("\nYou can now add a drink or chips, or create another sandwich.");
        System.out.println("Redirecting you to the order menu...");

        // 3 seconds
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void displayDrinkScreen() {
        System.out.println("Please select a drink: ");
        int drinkIndex = 1;
        for (Drink drink : store.drinks.values()) {
            System.out.println(drinkIndex++ + ") " + drink.getName());
        }
        int selectedDrinkIndex = Utils.getIntegerWithRange("Enter a number: ", true, 1, store.drinks.size());

        Drink selectedDrink = store.drinks.values().toArray(Drink[]::new)[selectedDrinkIndex - 1];
        System.out.println("Please choose a size: ");
        int drinkSizeIndex = 1;
        for (SizePrice sp : selectedDrink.getSizePrices()) {
            System.out.println(drinkSizeIndex++ + ")" + sp.getSize().getName());
        }
        int selectedSizeIndex = Utils.getIntegerWithRange("Enter a number: ", true, 1, selectedDrink.getSizePrices().length);
        SizePrice drinkSize = selectedDrink.getSizePrices()[selectedSizeIndex - 1];

        this.drinks.add(new Selection<>(selectedDrink, drinkSize));

        //TODO seperate UI AND BUSINESS LOGIC
    }

    public void displayChipScreen() {
        System.out.println("Please select a chip type: ");
        int chipIndex = 1;
        for (Chip chip : store.chips.values()) {
            System.out.println(chipIndex++ + ") " + chip.getName());
        }
        int selectedChipIndex = Utils.getIntegerWithRange("Enter a number: ", true, 1, store.chips.size());

        Chip selectedChip = store.chips.values().toArray(Chip[]::new)[selectedChipIndex - 1];

        System.out.println("Please choose a size: ");
        int chipSizeIndex = 1;
        for (SizePrice sp : selectedChip.getSizePrices()) {
            System.out.println(chipSizeIndex++ + ")" + sp.getSize().getName() + " - $" + sp.getPrice());
        }
        int selectedChipSizeIndex = Utils.getIntegerWithRange("Enter a number: ", true, 1, selectedChip.getSizePrices().length);
        SizePrice chipSize = selectedChip.getSizePrices()[selectedChipSizeIndex - 1];
        this.chips.add(new Selection<>(selectedChip, chipSize));
    }

    public void displayCheckoutScreen() throws IOException {
        StringBuilder out = new StringBuilder();
        out.append("Customer: " + customer.getName());
        out.append("\n");
        out.append("Order date: " + this.timeStamp.toString());
        out.append("\n");
        out.append("Sandwiches: ");
        out.append("\n");
        int sandwichIndex = 1;
        for (Sandwich sandwich : sandwiches) {
            String name = "Sandwich " + sandwichIndex;
            if (sandwich instanceof SignatureSandwich) {
                name = ((SignatureSandwich) sandwich).getName();
            }
            out.append("- " + name);
            out.append("\n");

            out.append("Bread Type: " + sandwich.getBreadSelection().getProduct().getName());
            out.append("\n");


            out.append("Toasted: " + (sandwich.isToasted() ? "Yes" : "No"));
            out.append("\n");


            out.append("Has extra meat: " + (sandwich.isHasExtraMeat() ? "Yes" : "No"));
            out.append("\n");

            out.append("Has extra cheese: " + (sandwich.isHasExtraCheese() ? "Yes" : "No"));
            out.append("\n");


            out.append("-- Toppings --");
            out.append("\n");

            for (Topping topping : sandwich.getToppingList()) {
                out.append("-" + topping.getName());
                out.append("\n");

            }
            out.append("-- Sauces --");
            out.append("\n");

            for (Sauce sauce : sandwich.getSauceList()) {
                out.append("-" + sauce.getName());
                out.append("\n");

            }
            out.append("-- Side --");
            out.append("\n");

            if (sandwich.getSide() == null) {
                out.append("No side selected");
            } else {
                out.append(sandwich.getSide().getName());
            }
            sandwichIndex++;

            out.append("\n");
            out.append("\n");

        }
        out.append("Drinks: ");
        out.append("\n");

        for (Selection<Drink> drink : drinks) {
            out.append(drink.getProduct().getName() + "-" + drink.getPricing().getSize().getName());
            out.append("\n");

        }
        out.append("Chips: ");
        out.append("\n");

        for (Selection<Chip> chip : chips) {
            out.append(chip.getProduct().getName() + "-" + chip.getPricing().getSize().getName());
            out.append("\n");

        }

        out.append(getTotal());

        out.append("\n");
        System.out.println(out.toString());
        out.append("\n");

        boolean confirmOrder = Utils.getStringFromTerminal("Confirm order? [Y]es / [N]o").equalsIgnoreCase("y");
        if (confirmOrder) {
            new Receipt(this).writeToFile(out.toString());
        }

    }

    public double getTotal() {
        int total = 0;
        for (Sandwich s : this.sandwiches) {
            total += s.getPrice();
        }
        for (Selection<Chip> c : this.chips) {
            total += c.getProduct().getPriceForSize(c.getPricing().getSize());
        }
        for (Selection<Drink> c : this.drinks) {
            total += c.getProduct().getPriceForSize(c.getPricing().getSize());
        }
        return total;
    }


}
