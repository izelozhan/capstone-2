package MenuItems;

import Pricing.OtherProduct;
import Pricing.SizePrice;

public class Topping extends OtherProduct {

    public Topping(String name, SizePrice[] pricing) {
        super(name, pricing);
    }

    public boolean isPremium(){
        return this.getSizePrices().length > 0;
    }

    public boolean isMeat() {
        return getName().startsWith("MEAT");
    }

    public boolean isCheese() {
        return getName().startsWith("CHEESE");
    }
}
