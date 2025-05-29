import Enums.*;
import MenuItems.*;
import Pricing.Size;
import Pricing.SizePrice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Store {
    public Map<BreadType, BreadSelection> breads = new HashMap<>();
    public Map<DrinkType, Drink> drinks = new HashMap<>();
    public Map<ToppingType, Topping> toppings = new HashMap<>();
    public Map<ChipType, Chip> chips = new HashMap<>();
    public Map<SauceType, Sauce> sauces = new HashMap<>();
    public Map<SideType, Side> sides = new HashMap<>();

    Size FOUR_INCHES = new Size("4");
    Size EIGHT_INCHES = new Size("8");
    Size TWELVE_INCHES = new Size("12");

    Size[] availableSizes = new Size[]{
            FOUR_INCHES,
            EIGHT_INCHES,
            TWELVE_INCHES
    };

    public Order createNewOrder(){
        return new Order(this);
    }

    public void prepare() {
        for (DrinkType flavorType : DrinkType.values()) {
            Drink drink = new Drink(flavorType.name(), new SizePrice[]{
                    new SizePrice(new Size("Small"), 2.00),
                    new SizePrice(new Size("Medium"), 2.50),
                    new SizePrice(new Size("Large"), 3.00),
            });

            drinks.put(flavorType, drink);
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
            chips.put(chipType, chip);
        }

        for (SauceType sauceType : SauceType.values()) {
            Sauce sauce = new Sauce(sauceType.name());
            sauces.put(sauceType, sauce);
        }

        for (ToppingType toppingType : ToppingType.values()) {
            Topping topping = new Topping(toppingType.name(), new SizePrice[0]);
            toppings.put(toppingType, topping);
        }

        for (SideType sideType : SideType.values()){
            Side side = new Side(sideType.name());
            sides.put(sideType, side);
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

    public ArrayList<SignatureSandwich> generateSignatureSandwiches() {
        ArrayList<SignatureSandwich> signatureSandwiches = new ArrayList<>();

        SignatureSandwich BLT = sandwichFactory("BLT", BreadType.WHITE, EIGHT_INCHES, new ToppingType[]{
                ToppingType.MEAT_BACON, ToppingType.TOMATOES, ToppingType.LETTUCE, ToppingType.CHEESE_CHEDDAR
        }, new SauceType[]{SauceType.RANCH}, true);

        SignatureSandwich PhillyCheese = sandwichFactory("Philly Cheese Steak", BreadType.WHITE, EIGHT_INCHES, new ToppingType[]{ToppingType.MEAT_STEAK, ToppingType.CHEESE_AMERICAN, ToppingType.PEPPERS}, new SauceType[]{SauceType.MAYO}, true);

        SignatureSandwich TurkeyClub = sandwichFactory("Turkey Club", BreadType.WHEAT, FOUR_INCHES, new ToppingType[]{ToppingType.CHEESE_SWISS, ToppingType.TOMATOES, ToppingType.LETTUCE, ToppingType.MEAT_TURKEY}, new SauceType[]{SauceType.MAYO}, false);

        signatureSandwiches.add(BLT);
        signatureSandwiches.add(PhillyCheese);
        signatureSandwiches.add(TurkeyClub);

        return signatureSandwiches;
    }

    public SignatureSandwich sandwichFactory(String name, BreadType breadType, Size size, ToppingType[] toppingTypes, SauceType[] saucesTypes, boolean isToasted) {

        SignatureSandwich signatureSandwich = new SignatureSandwich(name);
        signatureSandwich.setToasted(isToasted);

        BreadSelection bread = breads.get(breadType);

        signatureSandwich.setSize(size);
        signatureSandwich.setBread(bread);
        for (SauceType sauce : saucesTypes) {
            signatureSandwich.addSauce(this.sauces.get(sauce));
        }

        for (ToppingType topping : toppingTypes) {
            signatureSandwich.addTopping(this.toppings.get(topping));
        }

        return signatureSandwich;
    }
}