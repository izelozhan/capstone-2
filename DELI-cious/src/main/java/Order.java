import Enums.BreadType;
import Enums.ToppingType;
import Utilities.Utils;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class Order {
    String orderId;
    //   List <Meal> items;
    LocalDateTime timeStamp;
    static Customer customer;
    DELIcious store;

    public void init(DELIcious store) {
        this.store = store;
        Utils.printTitle("\n=== Welcome to Delicious Order Screen! ===");
        createCustomer();
        displayOrderScreen();
    }

    public void createCustomer() {
        String name;

        System.out.println("What's your name?");
        System.out.println("Enter your name: ");
        name = Utils.scanner.nextLine();

        customer = new Customer(name);
    }

    public void displayOrderScreen() {
        //TODO LOOP
        Utils.printTitle("\n=== Order Menu ===");
        Utils.printMenuOption("1) Add Sandwich");
        Utils.printMenuOption("2) Add Drink");
        Utils.printMenuOption("3) Add Chips");
        Utils.printMenuOption("4) Checkout");
        Utils.printMenuOption("0) Cancel Order");
        Utils.printMenuOption("Choose an option: ");

        int userChoice = Utils.getIntegerFromTerminal("", true);

        if (userChoice == 1) {
            System.out.println("Do you want to build your own sandwich or want to try our Signature Sandwiches?");
            System.out.println("1) Create Sandwich!");
            System.out.println("2) Signature Sandwiches");
            System.out.println("0) Go Back to Order Menu");

            int sandwichChoice = Utils.getIntegerFromTerminal("", true);

            if (sandwichChoice == 0) {
                //TODO
            }

            Sandwich sandwich = null;

            if (sandwichChoice == 1) {
                sandwich = new Sandwich();

                //SIZE
                System.out.println("Please select your size: ");
                for (int i = 0; i < store.availableSizes.length; i++) {
                    System.out.println(i + ")" + store.availableSizes[i].name);
                }

                int sizeSelectionIndex = Utils.getIntegerFromTerminal("Please select size: ", true);
                Size selectedSize = store.availableSizes[sizeSelectionIndex];

                //BREAD
                System.out.println("Please select your bread:");
                int breadIndex = 1;
                for (BreadType type : store.breads.keySet()) {
                    System.out.println(breadIndex++ + ")" + type.name());
                }

                int breadSelectionIndex = Utils.getIntegerFromTerminal("Please select bread: ", true);
                //converting keySet stream to Array
                BreadType breadType = store.breads.keySet().toArray(BreadType[]::new)[breadSelectionIndex];
                BreadSelection finalBread = store.breads.get(breadType);


                sandwich.setSize(selectedSize);
                sandwich.setBread(finalBread);


            } else if (sandwichChoice == 2) {
                ArrayList<SignatureSandwich> signatureSandwiches = store.generateSignatureSandwiches();
                for (int i = 0; i < signatureSandwiches.size(); i++) {
                    SignatureSandwich option = signatureSandwiches.get(i);
                    System.out.println(i + ") " + option.name + " - $" + option.getPrice());
                }

                int signatureChoice = Utils.getIntegerFromTerminal("Please select: ", true);

                sandwich = signatureSandwiches.get(signatureChoice);
            }

            if (!sandwich.toppingList.isEmpty()) {
                System.out.println("This sandwich comes with following toppins: ");
                for (Topping topping : sandwich.toppingList) {
                    System.out.println(topping.name);
                }
            }

            if (!sandwich.sauceList.isEmpty()) {
                System.out.println("This sandwich comes with following sauces");
                for (Sauce sauce : sandwich.sauceList) {
                    System.out.println(sauce.name);
                }
            }



            //TOPPINGS
            //TODO LOOP
            System.out.println("Select select any toppings you would like to add: ");
            int toppingIndex = 1;
            for (Topping topping : store.toppings.values()) {
                System.out.println(toppingIndex++ + ") " + topping.name);
            }

            int userTopping = Utils.getIntegerFromTerminal("Please enter topping number: ", true);
            if (userTopping == 0) {
                //TODO
            } else {
                Topping selectedTopping = store.toppings.values().toArray(Topping[]::new)[userTopping];
                sandwich.addTopping(selectedTopping);
            }

            //SAUCE
            System.out.println("Select select any sauces you would like to add: ");
            int sauceIndex = 1;
            for (Sauce sauce : store.sauces.values()) {
                System.out.println(sauceIndex++ + ") " + sauce.name);
            }

            int userSauce = Utils.getIntegerFromTerminal("Please enter sauce number: ", true);
            if (userSauce == 0) {
                //TODO
            } else {
                Sauce selectedSauce = store.sauces.values().toArray(Sauce[]::new)[userSauce];
                sandwich.addSauce(selectedSauce);
            }

            //toasted
            if (sandwich.isToasted) {
                System.out.println("This sandwich is toasted!");
            }

            boolean wantsToasted = Utils.getStringFromTerminal("Press Y to toast this sandwich").equalsIgnoreCase("y");
            sandwich.setToasted(wantsToasted);



        }
        else if(userChoice == 2){
            System.out.println("Please select a drink: ");
            int drinkIndex = 1;
            for (Drink drink : store.drinks.values()){
                System.out.println(drinkIndex++ + ") " + drink.name);
            }
            int selectedDrinkIndex = Utils.getIntegerFromTerminal("Enter a number: ", true);

            Drink selectedDrink = store.drinks.values().toArray(Drink[]::new)[selectedDrinkIndex];
            System.out.println("Please choose a size: ");
            int drinkSizeIndex = 1;
            for (SizePrice sp : selectedDrink.pricing){
                System.out.println(drinkSizeIndex++ + ")" + sp.size.name);
            }
            int selectedSizeIndex = Utils.getIntegerFromTerminal("Enter a number: ", true);
            SizePrice drinkSize = selectedDrink.pricing[selectedSizeIndex];
        } else if (userChoice == 3 ){
            System.out.println("Please select a chip type: ");
            int chipIndex = 1;
            for (Chip chip : store.chips.values()){
                System.out.println(chipIndex++ + ") " + chip.name);
            }
            int selectedChipIndex = Utils.getIntegerFromTerminal("Enter a number: ", true);

            Chip selectedChip = store.chips.values().toArray(Chip[]::new)[selectedChipIndex];

            System.out.println("Please choose a size: ");
            int chipSizeIndex = 1;
            for (SizePrice sp : selectedChip.pricing){
                System.out.println(chipSizeIndex++ + ")" + sp.size.name);
            }
            int selectedSizeIndex = Utils.getIntegerFromTerminal("Enter a number: ", true);
            SizePrice chipSize = selectedChip.pricing[selectedChipIndex];
        }



    }
}
// what do you want to add
// 1 - drink
// 2 - chips

// SYSTEM.OUT.PRINTLN("PICK BREAD")
// GETUSERSELECTION(BREADS);
// SYSTEM.OUT.PRINTLN("PICK DRINK")
// GETUSERSELECTION(DRINKS);
// GETTOTAL()

// GET USER SELECTION (ARRAY):
// var option = 1 -> drinks, 2 -> chips
// if(option == ARRAY) {
//  system.out.println("pick one");
//  foreach(var chipName : ARRAY.keySet()) {
//      System.out.println("{sayi} - {chipname}")
//  }
//  var chipSelection = System.in();
//  var selectedChipKey = ARRAY.keySet()[chipSelection]
//  var sizePrices = ARRAY.get(selectedChipKey);
//  if (sizePrices.length <= 1) // free, just add
//  //askw hich size they want
//  for(var sz : sizePrices) {
//    System.out.println("{size} - {sz.size.name} : {sz.price}
//  }
//  var szSelection = Scanner.in();
//  var priceIndex = sizePrices.keySet()[szSelection];
//  var selectedPrice = sizePrices[priceIndex];