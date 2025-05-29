package Pricing;

public class SizePrice {
    Size size;
    Double price;

    public SizePrice(Size size, Double price) {
        this.size = size;
        this.price = price;
    }

    public Size getSize() {
        return size;
    }

    public Double getPrice() {
        return price;
    }
}
