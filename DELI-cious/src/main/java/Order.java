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
            } else if (sandwichChoice == 2) {
                ArrayList<SandwichTemplate> signatureSandwiches = store.generateSignatureSandwiches();
                for (int i = 0; i < signatureSandwiches.size(); i++) {
                    SandwichTemplate option = signatureSandwiches.get(i);
                    System.out.println(i + ") " + option.name + " - $" + option.getPrice());
                }

                int signatureChoice = Utils.getIntegerFromTerminal("Please select: ", true);

                sandwich = signatureSandwiches.get(signatureChoice);
            }

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