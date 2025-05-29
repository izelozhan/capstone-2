package MenuItems;

import Pricing.SizePrice;

public class DrinkSelection {
    Drink drink;
    SizePrice size;

    public DrinkSelection(Drink drink, SizePrice size) {
        this.drink = drink;
        this.size = size;
    }

    public Drink getDrink() {
        return drink;
    }

    public SizePrice getSizePrice() {
        return size;
    }
}
