package Pricing;

import java.util.Arrays;
import java.util.Optional;

public class Product {
    String name;
    SizePrice[] pricing; //storing all sizes and prices for a product

    public Product(String name, SizePrice[] pricing) {
        this.name = name;
        this.pricing = pricing;
    }

    public String getName() {
        return name;
    }

    public String getFormattedNameWithPrice(Size size) {
        return name + " - " + (this.pricing.length == 0 ? "FREE!" : "$" + this.getPriceForSize(size));

    }

    public SizePrice[] getSizePrices() {
        return pricing;
    }

    public double getPriceForSize(Size size) {
        if(this.pricing.length == 0) {
            return 0;
        }
        // any price exists for the size?
        Optional<SizePrice> sizePrice = Arrays.stream(this.pricing).filter(i -> i.getSize().equals(size)).findFirst();
        if(sizePrice.isEmpty()) {
            return 0; //no price tag, so it's free lol
        } else {
            return sizePrice.get().getPrice();
        }
    }
}
