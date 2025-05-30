import Enums.BreadType;
import Enums.ExtraType;
import MenuItems.Sandwich;
import MenuItems.Selection;
import MenuItems.SignatureSandwich;
import MenuItems.Topping;
import Pricing.Product;
import Pricing.Size;
import Pricing.SizePrice;
import Utilities.Utils;

import java.util.Arrays;
import java.util.List;

public class SandwichScreen {
    Order order;
    Store store;
    Sandwich sandwich = null;
    boolean addTopping = true;
    boolean addSauce = true;
    boolean addSide = true;
    boolean isSignature = false;

    public SandwichScreen(Order order, Store store, boolean isSignature) {
        this.order = order;
        this.store = store;
        this.isSignature = isSignature;
    }

    public void display() {
        if(this.isSignature) {
            chooseSandwichBaseSignature();
        } else {
            chooseSandwichBaseRegular();
        }
        chooseToppings();
        chooseSauce();
        chooseToasted();
        chooseSides();
        finish();
    }

    // step 1
    private void chooseSandwichBaseRegular() {
        // create a blank sandwich
        this.sandwich = new Sandwich();

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
        Product finalBread = store.breads.get(breadType);
        SizePrice selectedBreadPrice = Arrays.stream(finalBread.getSizePrices()).filter(i -> i.getSize().equals(selectedSize)).findFirst().get();

        //Set size and bread
        sandwich.setBreadSelection(new Selection<Product>(finalBread, selectedBreadPrice));
    }

    private void chooseSandwichBaseSignature() {
        Utils.printTitle("\nOur Signature Sandwiches:");
        for (int i = 1; i <= store.signatureSandwiches.size(); i++) {
            SignatureSandwich option = store.signatureSandwiches.get(i - 1);
            System.out.println(i + ") " + option.getName() + " - $" + option.getPrice());
        }
        int signatureChoice = Utils.getIntegerWithRange("Please select sandwich: ", true, 1, store.signatureSandwiches.size());
        sandwich = store.signatureSandwiches.get(signatureChoice - 1);

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


    // step 2
    private void chooseToppings() {
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
                boolean exist = sandwich.getToppingList().contains(topping);
                String message = topping.getFormattedNameWithPrice(sandwich.getSelectedSize());
                if (!exist) {
                    Utils.printMenuOption(message);
                } else {
                    Utils.printExistTopping(message);
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
                if(input == 1) {
                    sandwich.addExtra(store.extras.get(ExtraType.EXTRA_MEAT));
                }
            }

            if (hasCheese) {
                Utils.printOrderSubtitles("\nYou have premium cheese. Would you like to add extra cheese?");
                Utils.printMenuOption("1) Yes!");
                Utils.printMenuOption("2) No.");

                int input = Utils.getIntegerWithRange("Please choose an option: ", true, 1, 2);
                if(input == 1) {
                    sandwich.addExtra(store.extras.get(ExtraType.EXTRA_CHEESE));
                }
            }
        }

    }

    // step 3
    private void chooseSauce() {
        while (addSauce) {
            Utils.printOrderSubtitles("Select select any sauces you would like to add: ");
            int sauceIndex = 1;
            for (Product sauce : store.sauces.values()) {
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
                Product selectedSauce = store.sauces.values().toArray(Product[]::new)[userSauce - 1];
                sandwich.addSauce(selectedSauce);
            }
        }
    }

    // step 4
    private void chooseToasted() {
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
    }

    // step 5
    private void chooseSides() {
        while (addSide) {
            Utils.printOrderSubtitles("\nSelect side you would like to add: ");
            int sideIndex = 1;
            for (Product side : store.sides.values()) {
                System.out.println(sideIndex++ + ") " + side.getName());
            }
            Utils.printMenuOption("0) Skip");

            int userSide = Utils.getIntegerWithRange("Please enter side number: ", true, 0, store.sides.size());
            if (userSide == 0) {
                addSide = false;
            } else {
                Product selectedSide = store.sides.values().toArray(Product[]::new)[userSide - 1];
                sandwich.setSide(selectedSide);
                addSide = false; // only one side
            }
        }
        System.out.println("Thank you! We added your sandwich to your order.");
    }

    // step 6
    private void finish() {
        order.sandwiches.add(sandwich);
        System.out.println("\nYou can now add a drink or chips, or create another sandwich.");
        System.out.println("Redirecting you to the order menu...");
        // 3 seconds
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
