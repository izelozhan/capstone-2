import Enums.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DELIcious {
    public Map<BreadType, BreadSelection> breads = new HashMap<>();
    public Map<String, Drink> drinks = new HashMap<>();
    public Map<ToppingType, Topping> toppings = new HashMap<>();
    public Map<String, Chip> chips = new HashMap<>();
    public Map<String, Sauce> sauces = new HashMap<>();

    static Scanner scanner = new Scanner(System.in);

    Size FOUR_INCHES = new Size("4");
    Size EIGHT_INCHES = new Size("8");
    Size TWELVE_INCHES = new Size("12");


    public static void main(String[] args) {
        boolean isRunning = true;
        prepare();

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

    public void prepare() {
        for (DrinkType flavorType : DrinkType.values()) {
            Drink drink = new Drink(flavorType.name(), new SizePrice[]{
                    new SizePrice(new Size("Small"), 2.00),
                    new SizePrice(new Size("Medium"), 2.50),
                    new SizePrice(new Size("Large"), 3.00),
            });

            drinks.put(flavorType.name(), drink);
        }

        for (BreadType breadType : BreadType.values()) {
            BreadSelection bread = new BreadSelection(breadType.name(), new SizePrice[]{
                    new SizePrice(FOUR_INCHES, 5.50),
                    new SizePrice(EIGHT_INCHES, 7.00),
                    new SizePrice(TWELVE_INCHES, 8.50),
            });

            breads.put(breadType, bread);
        }

        for (ChipType chipType : ChipType.values()) {
            Chip chip = new Chip(chipType.name(), new SizePrice[]{
                    new SizePrice(new Size("Standard"), 1.50)
            });
            chips.put(chipType.name(), chip);
        }

        for (SauceType sauceType : SauceType.values()) {
            Sauce sauce = new Sauce(sauceType.name());
            sauces.put(sauceType.name(), sauce);
        }

        for (ToppingType toppingType : ToppingType.values()) {
            Topping topping = new Topping(toppingType.name(), new SizePrice[0]);
            toppings.put(toppingType.name(), topping);
        }

        ToppingType[] meats = new ToppingType[]{
                ToppingType.MEAT_TURKEY,
                ToppingType.MEAT_CHICKEN,
                ToppingType.MEAT_BACON,
                ToppingType.MEAT_HAM,
                ToppingType.MEAT_SALAMI,
                ToppingType.MEAT_STEAK,
                ToppingType.MEAT_ROAST_BEEF
        };

        for (ToppingType meatType : meats) {
            Topping topping = new Topping(meatType.name(), new SizePrice[]{
                    new SizePrice(FOUR_INCHES, 1.00),
                    new SizePrice(EIGHT_INCHES, 2.00),
                    new SizePrice(TWELVE_INCHES, 3.00),
            });
            toppings.put(meatType, topping);
        }

        ToppingType[] cheeses = new ToppingType[]{
                ToppingType.CHEESE_AMERICAN,
                ToppingType.CHEESE_CHEDDAR,
                ToppingType.CHEESE_SWISS,
                ToppingType.CHEESE_PARMESAN,
                ToppingType.CHEESE_PROVOLONE
        };

        for (ToppingType cheeseType : cheeses) {
            Topping topping = new Topping(cheeseType.name(), new SizePrice[]{
                    new SizePrice(FOUR_INCHES, 0.75),
                    new SizePrice(EIGHT_INCHES, 1.50),
                    new SizePrice(TWELVE_INCHES, 2.25),
            });
            toppings.put(cheeseType, topping);
        }

    }

    public ArrayList<SandwichTemplate> generateSignatureSandwiches() {
        ArrayList<SandwichTemplate> signatureSandwiches = new ArrayList<>();

        SandwichTemplate BLT = sandwichFactory("BLT", BreadType.WHITE, EIGHT_INCHES, new ToppingType[]{
                ToppingType.MEAT_BACON, ToppingType.TOMATOES, ToppingType.LETTUCE, ToppingType.CHEESE_CHEDDAR
        }, new SauceType[]{SauceType.RANCH}, true);

        SandwichTemplate PhillyCheese = sandwichFactory("Philly Cheese Steak", BreadType.WHITE, EIGHT_INCHES, new ToppingType[]{ToppingType.MEAT_STEAK, ToppingType.CHEESE_AMERICAN, ToppingType.PEPPERS}, new SauceType[]{SauceType.MAYO}, true);

        SandwichTemplate TurkeyClub = sandwichFactory("Turkey Club", BreadType.WHEAT, FOUR_INCHES, new ToppingType[]{ToppingType.CHEESE_SWISS, ToppingType.TOMATOES, ToppingType.LETTUCE, ToppingType.MEAT_TURKEY}, new SauceType[]{ SauceType.MAYO}, false);

        signatureSandwiches.add(BLT);
        signatureSandwiches.add(PhillyCheese);
        signatureSandwiches.add(TurkeyClub);

        return signatureSandwiches;
    }

    public SandwichTemplate sandwichFactory(String name, BreadType breadType, Size size, ToppingType[] toppingTypes, SauceType[] saucesTypes, boolean isToasted) {

        SandwichTemplate signatureSandwich = new SandwichTemplate(name);
        signatureSandwich.isToasted = isToasted;

        BreadSelection bread = breads.get(breadType);
        SizePrice breadSizePrice = bread.pricing.stream().filter(s -> s.size.equals(size)).findFirst().get();

        for (SauceType sauce : saucesTypes) {
            signatureSandwich.addSauce(this.sauces.get(sauce));
        }

        for (ToppingType topping : toppingTypes) {
            signatureSandwich.addTopping(this.toppings.get(topping));
        }

        return signatureSandwich;
    }
}