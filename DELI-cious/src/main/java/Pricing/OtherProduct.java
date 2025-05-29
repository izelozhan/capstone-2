package Pricing;

public class OtherProduct {
    String name;
    SizePrice[] pricing;

    public OtherProduct(String name, SizePrice[] pricing) {
        this.name = name;
        this.pricing = pricing;
    }

    public String getName() {
        return name;
    }

    public SizePrice[] getPricing() {
        return pricing;
    }
}
