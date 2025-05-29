package MenuItems;

import Pricing.OtherProduct;
import Pricing.SizePrice;

public class Selection<T extends OtherProduct>  {
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

}
