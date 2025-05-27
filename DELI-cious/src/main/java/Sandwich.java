import Enums.*;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    Size size;
    BreadType breadType;
    boolean isToasted;
    double totalPrice;

    List<MeatType> meatTypeList = new ArrayList<>();
    List<CheeseType> cheeseTypeList = new ArrayList<>();
    List<SauceType> souceList = new ArrayList<>();

    public Sandwich(BreadType breadType, Size size, boolean toasted) {
        this.breadType = breadType;
        this.size = size;
        this.isToasted = toasted;
    }

    public void addMeat(MeatType meatType, boolean isExtra) {
        meatTypeList.add(meatType);
        switch (size) {
            case FOUR_INCH -> totalPrice += 1.00;
            case EIGHT_INCH -> totalPrice += 2.00;
            case TWELVE_INCH -> totalPrice += 3.00;
        }
        if (isExtra){
            switch (size) {
                case FOUR_INCH -> totalPrice += .50;
                case EIGHT_INCH -> totalPrice += 1.00;
                case TWELVE_INCH -> totalPrice += 1.50;
            }
        }
    }

    public void addCheese(CheeseType cheeseType, boolean isExtra) {
        cheeseTypeList.add(cheeseType);
        switch (size) {
            case FOUR_INCH -> totalPrice += .75;
            case EIGHT_INCH -> totalPrice += 1.50;
            case TWELVE_INCH -> totalPrice += 2.25;
        }
        if (isExtra){
            switch (size) {
                case FOUR_INCH -> totalPrice += .30;
                case EIGHT_INCH -> totalPrice += .60;
                case TWELVE_INCH -> totalPrice += .90;
            }
        }
    }


}


