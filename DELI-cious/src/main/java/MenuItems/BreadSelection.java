package MenuItems;

import Pricing.SizePrice;

import java.util.Arrays;
import java.util.List;

public class BreadSelection {
    String breadType;
    List<SizePrice> pricing;

    public BreadSelection(String breadType, SizePrice[] pricing) {
        this.pricing = Arrays.stream(pricing).toList();
        this.breadType = breadType;
    }

    public String getBreadType() {
        return breadType;
    }

    public List<SizePrice> getPricing() {
        return pricing;
    }
}
