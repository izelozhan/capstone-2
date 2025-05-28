import Old.Enums.*;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    BreadSelection bread;
    List <Topping> toppingList;
    List <Sauce> sauceList;
    boolean isToasted;


    public Sandwich(BreadSelection bread, List<Topping> toppingList, List<Sauce> sauceList, boolean isToasted) {
        this.bread = bread;
        this.toppingList = toppingList;
        this.sauceList = sauceList;
        this.isToasted = isToasted;
    }

    public static void addTopping(){

    }

    public static void removeTopping(){

    }

    public static void getSize(){

    }
}


