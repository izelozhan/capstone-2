package MenuItems;

import Pricing.Product;
import Pricing.Size;
import Pricing.SizePrice;

// storing a product with it's [size and price] ( user selected )
// sandwich bread, drinks and chips uses this
public class Selection<T extends Product>  {
    T product;
    SizePrice pricing;

    public Selection(T product, SizePrice pricing) {
        this.product = product;
        this.pricing = pricing;
    }

    public T getProduct() {
        return product;
    }

    public SizePrice getPricing() {
        return pricing;
    }

    //Overriding toString so it can write "name (size) $0.00"
    @Override
    public String toString() {
        return  this.getProduct().getName() +
                "(" +
                this.getPricing().getSize().getName() +
                ") " +
                "\t" +
                "$" +
                String.format("%.2f", this.getPricing().getPrice());
    }

}
