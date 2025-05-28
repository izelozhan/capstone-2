import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    BreadSelection bread;
    List<Topping> toppingList;
    List<Sauce> sauceList;
    boolean isToasted;

    public Sandwich() {
        this.toppingList = new ArrayList<>();
        this.sauceList = new ArrayList<>();
        this.isToasted = false;
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

    public static void removeTopping() {

    }

    public int getPrice(){
        return 0;
    }
}


