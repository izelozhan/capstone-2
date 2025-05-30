package MenuItems;

import Pricing.Product;
import Pricing.Size;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    Selection<Product> breadSelection;
    List<Topping> toppingList;
    List<Product> sauceList;
    List<Product> extras;
    Product side;
    boolean isToasted;

    public Sandwich() {
        this.toppingList = new ArrayList<>();
        this.sauceList = new ArrayList<>();
        this.extras = new ArrayList<>();
        this.isToasted = false;
    }

    public double getPrice() {
        double total = this.breadSelection.getPricing().getPrice();
        Size breadSize = this.breadSelection.getPricing().getSize();
        for (Product p : this.getSauceList()) {
            total += p.getPriceForSize(breadSize);
        }
        for (Product p : this.getToppingList()) {
            total += p.getPriceForSize(breadSize);
        }
        if (this.side != null) {
            total += this.side.getPriceForSize(breadSize);
        }

        for(Product p : this.getExtras()) {
            total += p.getPriceForSize(breadSize);
        }

        return total;
    }

    public void setBreadSelection(Selection<Product> selectedBread) {
        this.breadSelection = selectedBread;
    }

    public void addOrRemoveTopping(Topping topping) {
        if (!toppingList.contains(topping)) {
            toppingList.add(topping);
        } else {
            toppingList.remove(topping);
        }
    }

    public void addOrRemoveSauce(Product sauce) {
        if (!sauceList.contains(sauce)){
            sauceList.add(sauce);
        } else {
            sauceList.remove(sauce);
        }
    }

    public void setSide(Product side) {
        this.side = side;
    }

    public void setToasted(boolean toasted) {
        isToasted = toasted;
    }

    public Selection<Product> getBreadSelection() {
        return breadSelection;
    }

    public List<Topping> getToppingList() {
        return toppingList;
    }

    public List<Product> getExtras() {
        return extras;
    }

    public List<Product> getSauceList() {
        return sauceList;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public void addExtra(Product extra) {
        this.extras.add(extra);
    }

    public Product getSide() {
        return side;
    }

    public Size getSelectedSize() {
       return this.getBreadSelection().getPricing().getSize();
    }

}


