package MenuItems;

import Pricing.Product;
import Pricing.Size;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    Selection<Product> breadSelection;
    List<Topping> toppingList;
    List<Product> sauceList;
    Product side;
    boolean isToasted;
    boolean hasExtraMeat;
    boolean hasExtraCheese;

    public Sandwich() {
        this.toppingList = new ArrayList<>();
        this.sauceList = new ArrayList<>();
        this.isToasted = false;
    }

    public void setBreadSelection(Selection<Product> selectedBread) {
        this.breadSelection = selectedBread;
    }

    public void addTopping(Topping topping) {
        if (!toppingList.contains(topping)) {
            toppingList.add(topping);
        }
    }

    public void addSauce(Product sauce) {
        if (!sauceList.contains(sauce)){
            sauceList.add(sauce);
        }
    }

    public void setSide(Product side) {
        this.side = side;
    }

    public static void removeTopping() {

    }

    public int getPrice() {
        int total = 0;
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

        if (this.hasExtraMeat) {
            total += 2; //todo
        }
        if (this.hasExtraCheese) {
            total += 2; //todo
        }

        return total;
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

    public List<Product> getSauceList() {
        return sauceList;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public void setHasExtraCheese(boolean hasExtraCheese) {
        this.hasExtraCheese = hasExtraCheese;
    }

    public void setHasExtraMeat(boolean hasExtraMeat) {
        this.hasExtraMeat = hasExtraMeat;
    }

    public Product getSide() {
        return side;
    }

    public boolean isHasExtraMeat() {
        return hasExtraMeat;
    }

    public boolean isHasExtraCheese() {
        return hasExtraCheese;
    }
}


