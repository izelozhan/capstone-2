package Models;

import Enums.*;
import MenuItems.*;
import Pricing.Product;
import Pricing.Size;
import Pricing.SizePrice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Store {
    public Map<BreadType, Product> breads = new HashMap<>();
    public Map<DrinkType, Product> drinks = new HashMap<>();
    public Map<ChipType, Product> chips = new HashMap<>();
    public Map<SauceType, Product> sauces = new HashMap<>();
    public Map<SideType, Product> sides = new HashMap<>();
    public Map<ExtraType, Product> extras = new HashMap<>();

    public Map<ToppingType, Topping> toppings = new HashMap<>();
    public ArrayList<SignatureSandwich> signatureSandwiches = new ArrayList<>();


    Size FOUR_INCHES = new Size("4 inch");
    Size EIGHT_INCHES = new Size("8 inch");
    Size TWELVE_INCHES = new Size("12 inch");

    public Size[] availableSizes = new Size[]{
            FOUR_INCHES,
            EIGHT_INCHES,
            TWELVE_INCHES
    };

    public Store() {
        this.prepare();
        this.generateSignatureSandwiches();
    }

    public Order createNewOrder(){
        return new Order(this);
    }

    private void prepare() {
        // independent sizes from bread
        for (DrinkType flavorType : DrinkType.values()) {
            Product drink = new Product(flavorType.name(), new SizePrice[]{
                    new SizePrice(new Size("Small"), 2.00),
                    new SizePrice(new Size("Medium"), 2.50),
                    new SizePrice(new Size("Large"), 3.00),
            });
            drinks.put(flavorType, drink);
        }
        for (ChipType chipType : ChipType.values()) {
            Product chip = new Product(chipType.name(), new SizePrice[]{
                    new SizePrice(new Size("Standard"), 1.50)
            });
            chips.put(chipType, chip);
        }


        // dependent size on bread
        for (BreadType breadType : BreadType.values()) {
            Product bread = new Product(breadType.name(), new SizePrice[]{
                    new SizePrice(FOUR_INCHES, 5.50),
                    new SizePrice(EIGHT_INCHES, 7.00),
                    new SizePrice(TWELVE_INCHES, 8.50),
            });

            breads.put(breadType, bread);
        }

        for (SauceType sauceType : SauceType.values()) {
            Product sauce = new Product(sauceType.name(), new SizePrice[0]);
            sauces.put(sauceType, sauce);
        }

        // sides are free - no size price
        for (SideType sideType : SideType.values()){
            Product side = new Product(sideType.name(), new SizePrice[0]);
            sides.put(sideType, side);
        }

        // regular toppings that are free
        for (ToppingType toppingType : ToppingType.values()) {
            Topping topping = new Topping(toppingType.name(), new SizePrice[0]);
            toppings.put(toppingType, topping);
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

        // ADDING extra meat as a product with prices
        extras.put(ExtraType.EXTRA_MEAT, new Product(ExtraType.EXTRA_MEAT.name(), new SizePrice[] {
                new SizePrice(FOUR_INCHES, 0.50),
                new SizePrice(EIGHT_INCHES, 1.00),
                new SizePrice(TWELVE_INCHES, 1.50)
        }));

        extras.put(ExtraType.EXTRA_CHEESE, new Product(ExtraType.EXTRA_CHEESE.name(), new SizePrice[] {
                new SizePrice(FOUR_INCHES, 0.30),
                new SizePrice(EIGHT_INCHES, 0.60),
                new SizePrice(TWELVE_INCHES, 0.90)
        }));
    }

    private void generateSignatureSandwiches() {
        ArrayList<SignatureSandwich> signatureSandwiches = new ArrayList<>();

        SignatureSandwich BLT = sandwichFactory(
                "BLT",
                BreadType.WHITE,
                EIGHT_INCHES,
                new ToppingType[]{
                    ToppingType.MEAT_BACON,
                    ToppingType.TOMATOES,
                    ToppingType.LETTUCE,
                    ToppingType.CHEESE_CHEDDAR
                },
                new SauceType[]{
                    SauceType.RANCH
                }, true);

        SignatureSandwich PhillyCheese = sandwichFactory(
                "Philly Cheese Steak",
                BreadType.WHITE, EIGHT_INCHES,
                new ToppingType[]{
                    ToppingType.MEAT_STEAK,
                    ToppingType.CHEESE_AMERICAN,
                    ToppingType.PEPPERS
                },
                new SauceType[]{
                    SauceType.MAYO
                }, true);

        SignatureSandwich TurkeyClub = sandwichFactory(
                "Turkey Club",
                BreadType.WHEAT,
                FOUR_INCHES,
                new ToppingType[]{
                    ToppingType.CHEESE_SWISS,
                    ToppingType.TOMATOES,
                    ToppingType.LETTUCE,
                    ToppingType.MEAT_TURKEY
                },
                new SauceType[]{
                    SauceType.MAYO
                }, false);

        signatureSandwiches.add(BLT);
        signatureSandwiches.add(PhillyCheese);
        signatureSandwiches.add(TurkeyClub);

        this.signatureSandwiches = signatureSandwiches;
    }

    private SignatureSandwich sandwichFactory(String name, BreadType breadType, Size size, ToppingType[] toppingTypes, SauceType[] saucesTypes, boolean isToasted) {
        SignatureSandwich signatureSandwich = new SignatureSandwich(name);
        signatureSandwich.setToasted(isToasted);

        Product bread = breads.get(breadType);
        SizePrice breadPrice = Arrays.stream(bread.getSizePrices()).filter(i -> i.getSize().equals(size)).findFirst().get();
        Selection<Product> breadSelection = new Selection<>(bread, breadPrice);
        signatureSandwich.setBreadSelection(breadSelection);

        for (SauceType sauce : saucesTypes) {
            signatureSandwich.addOrRemoveSauce(this.sauces.get(sauce));
        }

        for (ToppingType topping : toppingTypes) {
            signatureSandwich.addOrRemoveTopping(this.toppings.get(topping));
        }

        return signatureSandwich;
    }

}