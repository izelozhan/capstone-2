import Enums.BreadType;
import MenuItems.*;
import Pricing.Size;
import Pricing.SizePrice;
import Utilities.Utils;

import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Order {
    ArrayList<Sandwich> sandwiches;
    ArrayList<DrinkSelection> drinks;
    ArrayList<ChipSelection> chips;

    LocalDateTime timeStamp;
    Customer customer;
    Store store;
    ArrayList<SignatureSandwich> signatureSandwiches;
    static String WELCOME_MESSAGE = "\n=== Welcome to Delicious Order Screen! ===";

    public Order(Store store) {
        this.store = store;
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();
        this.timeStamp = LocalDateTime.now();
        this.signatureSandwiches = store.generateSignatureSandwiches();
    }

    public void init() throws IOException {
        Utils.printTitle(WELCOME_MESSAGE);
        this.customer = new Customer(Utils.getStringFromTerminal("What's your name?"));
        displayOrderScreen();
    }

    public void displayOrderScreen() throws IOException {
        boolean userCanceled = false;

        while (!userCanceled) {
            Utils.printTitle("\n=== Order Menu ===");
            Utils.printMenuOption("1) Add Sandwich");
            Utils.printMenuOption("2) Add Drink");
            Utils.printMenuOption("3) Add Chips");
            Utils.printMenuOption("4) Checkout");
            Utils.printMenuOption("0) Cancel Order");
            Utils.printMenuOption("Choose an option: ");

            int userChoice = Utils.getIntegerFromTerminal("", true);

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
            Utils.printTitle("Do you want to build your own sandwich or want to try our Signature Sandwiches?");
            Utils.printMenuOption("1) Create Sandwich!");
            Utils.printMenuOption("2) Signature Sandwiches");
            Utils.printMenuOption("0) Go Back to Order Menu");

            int sandwichChoice = Utils.getIntegerFromTerminal("", true);

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

        //SANDWICH
        if (!isSignature) {
            sandwich = new Sandwich();

            //SIZE
            System.out.println("Please select your size: ");
            for (int i = 1; i <= store.availableSizes.length; i++) {
                System.out.println(i + ")" + store.availableSizes[i - 1].getName());
            }

            int sizeIndex = Utils.getIntegerWithRange("Please select size: ", true, 1, store.availableSizes.length);
            Size selectedSize = store.availableSizes[sizeIndex - 1];

            //BREAD
            System.out.println("Please select your bread:");
            int breadIndex = 1;
            for (BreadType type : store.breads.keySet()) {
                System.out.println(breadIndex++ + ")" + type.name());
            }

            breadIndex = Utils.getIntegerWithRange("Please select bread: ", true, 1, store.breads.size());

            //converting keySet stream to Array
            BreadType breadType = store.breads.keySet().toArray(BreadType[]::new)[breadIndex - 1];
            BreadSelection finalBread = store.breads.get(breadType);

            //Set size and bread
            sandwich.setSize(selectedSize);
            sandwich.setBread(finalBread);


        } else {
            //Signature Sandwich
            for (int i = 1; i <= signatureSandwiches.size(); i++) {
                SignatureSandwich option = signatureSandwiches.get(i - 1);
                System.out.println(i + ") " + option.getName() + " - $" + option.getPrice());
            }

            int signatureChoice = Utils.getIntegerWithRange("Please select: ", true, 1, signatureSandwiches.size());
            sandwich = signatureSandwiches.get(signatureChoice);

            if (!sandwich.getToppingList().isEmpty()) {
                System.out.println("This sandwich comes with following toppins: ");
                for (Topping topping : sandwich.getToppingList()) {
                    System.out.println(topping.getName());
                }
            }

            if (!sandwich.getSauceList().isEmpty()) {
                System.out.println("This sandwich comes with following sauces");
                for (Sauce sauce : sandwich.getSauceList()) {
                    System.out.println(sauce.getName());
                }
            }

            if (sandwich.isToasted()) {
                System.out.println("This sandwich is toasted!");
            }
        }

        //ADD TOPPINGS
        while (addTopping) {
            System.out.println("Select any toppings you would like to add: ");

            int toppingIndex = 1;

            for (Topping topping : store.toppings.values()) {
                Utils.printMenuOption(toppingIndex++ + ") " + topping.getName());
            }

            Utils.printMenuOption("0) Skip");

            int userTopping = Utils.getIntegerWithRange("Please enter topping number: ", true, 0, store.toppings.size());

            if (userTopping != 0) {
                Topping selectedTopping = store.toppings.values().toArray(Topping[]::new)[userTopping - 1];
                sandwich.addTopping(selectedTopping);
            } else {
                addTopping = false;
            }
        }

        if (!sandwich.getToppingList().isEmpty()){
            List<Topping> existingToppings = sandwich.getToppingList();
            boolean hasMeat = existingToppings.stream().anyMatch(i -> i.isPremium() && i.isMeat());
            boolean hasCheese = existingToppings.stream().anyMatch(i -> i.isPremium() && i.isCheese());

            if (hasMeat){
                System.out.println("You added premium meat. Would you like to add extra meat?");
                boolean exMeat = Utils.getStringFromTerminal("(Y)es (+ $0.50) / (N)o").equalsIgnoreCase("y");
                sandwich.setHasExtraMeat(exMeat);
            }

            if(hasCheese){
                System.out.println("You added premium cheese. Would you like to add extra cheese?");
                boolean exCheese = Utils.getStringFromTerminal("(Y)es (+ $0.50) / (N)o").equalsIgnoreCase("y");
                sandwich.setHasExtraCheese(exCheese);
            }
        }

        //ADD SAUCE
        while (addSauce) {
            System.out.println("Select select any sauces you would like to add: ");
            int sauceIndex = 1;
            for (Sauce sauce : store.sauces.values()) {
                System.out.println(sauceIndex++ + ") " + sauce.getName());
            }
            Utils.printMenuOption("0) Skip");

            int userSauce = Utils.getIntegerWithRange("Please enter sauce number: ", true, 0, store.sauces.size());
            if (userSauce == 0) {
                addSauce = false;
            } else {
                Sauce selectedSauce = store.sauces.values().toArray(Sauce[]::new)[userSauce - 1];
                sandwich.addSauce(selectedSauce);
            }
        }

        //IS TOASTED
        boolean wantsToasted = Utils.getStringFromTerminal("Press Y to toast this sandwich").equalsIgnoreCase("y");
        sandwich.setToasted(wantsToasted);

        //SIDES
        while (addSide) {
            System.out.println("Select select side you would like to add: ");
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


        this.sandwiches.add(sandwich);
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
        for (SizePrice sp : selectedDrink.getPricing()) {
            System.out.println(drinkSizeIndex++ + ")" + sp.getSize().getName());
        }
        int selectedSizeIndex = Utils.getIntegerWithRange("Enter a number: ", true, 1, selectedDrink.getPricing().length);
        SizePrice drinkSize = selectedDrink.getPricing()[selectedSizeIndex - 1];

        this.drinks.add(new DrinkSelection(selectedDrink, drinkSize));
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
        for (SizePrice sp : selectedChip.getPricing()) {
            System.out.println(chipSizeIndex++ + ")" + sp.getSize().getName());
        }
        int selectedChipSizeIndex = Utils.getIntegerWithRange("Enter a number: ", true, 1, selectedChip.getPricing().length);
        SizePrice chipSize = selectedChip.getPricing()[selectedChipSizeIndex - 1];

        this.chips.add(new ChipSelection(selectedChip, chipSize));
    }

    public void displayCheckoutScreen() throws IOException {
        StringBuilder out = new StringBuilder();
        out.append("Customer: " + customer.getName());
        out.append("Order date: " + this.timeStamp.toString());
        out.append("Sandwiches: ");
        int sandwichIndex = 1;
        for (Sandwich sandwich : sandwiches){
            String name = "Sandwich " + sandwichIndex;
            if (sandwich instanceof SignatureSandwich){
                name = ((SignatureSandwich) sandwich).getName();
            }
            out.append("- " + name);
            out.append("Bread Type: " + sandwich.getBread().getBreadType());

            out.append("Toasted: " + (sandwich.isToasted() ? "Yes" : "No"));

            out.append("Has extra meat: " + (sandwich.isHasExtraMeat() ? "Yes" : "No"));
            out.append("Has extra cheese: " + (sandwich.isHasExtraCheese() ? "Yes" : "No"));

            out.append("-- Toppings --");
            for (Topping topping : sandwich.getToppingList()){
                out.append("-" + topping.getName());
            }
            out.append("-- Sauces --");
            for (Sauce sauce : sandwich.getSauceList()){
                out.append("-" + sauce.getName());
            }
            out.append("-- Side --");
            if (sandwich.getSide() == null){
                out.append("No side selected");
            } else {
                out.append(sandwich.getSide().getName());
            }
            sandwichIndex++;
        }
        out.append("Drinks: ");
        for (DrinkSelection drink : drinks){
            out.append(drink.getDrink().getName() + "-" + drink.getSizePrice().getSize().getName());
        }
        out.append("Chips: ");
        for (ChipSelection chip : chips){
            out.append(chip.getChip().getName() + "-" + chip.getSizePrice().getSize().getName());
        }

        System.out.println(out.toString());

        boolean confirmOrder = Utils.getStringFromTerminal("Confirm order? [Y]es / [N]o").equalsIgnoreCase("y");
        if(confirmOrder) {
            new Receipt(this).writeToFile(out.toString());
        }

    }
}
