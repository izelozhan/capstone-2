package MenuItems;

import Pricing.Size;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    Size size;
    BreadSelection bread;
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

    public void setSize(Size size){
        this.size = size;
    }

    public void setBread(BreadSelection selectedBread) {
        this.bread = selectedBread;
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
        return 0;
    }

    public void setToasted(boolean toasted) {
        isToasted = toasted;
    }

    public Size getSize() {
        return size;
    }

    public BreadSelection getBread() {
        return bread;
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


