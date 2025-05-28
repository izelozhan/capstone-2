import Enums.BreadType;
import Enums.ChipType;
import Enums.DrinkFlavor;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DELIcious {
    public static Map<String, BreadSelection> breads = new HashMap<>();
    public static Map<String, Drink> drinks = new HashMap<>();
    public static Map<String, Topping> toppings = new HashMap<>();
    public static Map<String, Chip> chips = new HashMap<>();

    public static void prepare() {
        for (DrinkFlavor flavor : DrinkFlavor.values()) {
            Drink drink = new Drink(flavor.name(), new SizePrice[]{
                    new SizePrice(new Size("Small"), 2.00),
                    new SizePrice(new Size("Medium"), 2.50),
                    new SizePrice(new Size("Large"), 3.00),
            });

            drinks.put(flavor.name(), drink);
        }

        for (BreadType breadType : BreadType.values()) {
            BreadSelection bread = new BreadSelection(breadType.name(), new SizePrice[]{
                    new SizePrice(new Size("4"), 5.50),
                    new SizePrice(new Size("8"), 7.00),
                    new SizePrice(new Size("Large"), 8.50),
            });

            breads.put(breadType.name(), bread);
        }

        for (ChipType chipType : ChipType.values()){
            Chip chip = new Chip(chipType.name(), new SizePrice[] {
               new SizePrice(new Size("Standard"), 1.50)
            });
            chips.put(chipType.name(), chip);
        }

        SizePrice smallPrice = new SizePrice(new Size("Small"), 12);
        SizePrice mediumPrice = new SizePrice(new Size("Medium"), 14);
        SizePrice largePrice = new SizePrice(new Size("Large"), 16);
        String[] drinkTypes = new String[]{"Cola", "Fanta", "Ayran"};
        for (String type : drinkTypes) {
            Drink drink = new Drink(type);

            drinkMapping.put(drink, new ArrayList<>());
            drinkMapping.get(drink).put(smallPrice);
            drinkMapping.get(drink).put(mediumPrice);
            drinkMapping.get(drink).put(largePrice);
        }

        Drink foundCola = drinkMapping.keySet().stream().findFirst(i -> i.name == "Cola");
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Welcome to the DELIcious!");
            System.out.println("1) Create New Order ");
            System.out.println("0) Exit");

            int userInput = Integer.parseInt(scanner.nextLine());

            switch (userInput) {
                case 1 -> Order.init();
                case 0 -> {
                    System.out.println("Thank you! Goodbye!");
                    isRunning = false;
                }
                default -> System.out.println("Invalid input. Please try again.");
            }
        }
    }
}