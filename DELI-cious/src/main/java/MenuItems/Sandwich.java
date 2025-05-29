package MenuItems;

import Pricing.OtherProduct;
import Pricing.Size;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    Selection<Bread> breadSelection;
    List<Topping> toppingList;
    List<Sauce> sauceList;
    Side side;
    boolean isToasted;
    boolean hasExtraMeat;
    boolean hasExtraCheese;

    public Sandwich() {
        this.toppingList = new ArrayList<>();
        this.sauceList = new ArrayList<>();
        this.isToasted = false;
    }

    public void setBreadSelection(Selection<Bread> selectedBread) {
        this.breadSelection = selectedBread;
    }

    public void addTopping(Topping topping) {
        toppingList.add(topping);
    }

    public void addSauce(Sauce sauce) {
        sauceList.add(sauce);
    }

    public void setSide(Side side){
        this.side = side;
    }

    public static void removeTopping() {

    }

    public int getPrice(){
        int total = 0;
        Size breadSize = this.breadSelection.getPricing().getSize();
        for(OtherProduct p : this.getSauceList()) {
            total += p.getPriceForSize(breadSize);
        }
        for(OtherProduct p : this.getToppingList()) {
            total += p.getPriceForSize(breadSize);
        }
        if(this.side != null) {
            total += this.side.getPriceForSize(breadSize);
        }

        if(this.hasExtraMeat) {
            total += 2; //todo
        }
        if(this.hasExtraCheese) {
            total += 2; //todo
        }

        return total;
    }

    public void setToasted(boolean toasted) {
        isToasted = toasted;
    }

    public Selection<Bread> getBreadSelection() {
        return breadSelection;
    }

    public List<Topping> getToppingList() {
        return toppingList;
    }

    public List<Sauce> getSauceList() {
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

    public Side getSide() {
        return side;
    }

    public boolean isHasExtraMeat() {
        return hasExtraMeat;
    }

    public boolean isHasExtraCheese() {
        return hasExtraCheese;
    }
}


