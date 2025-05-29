import Utilities.Utils;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        boolean userExit = false;
        while(!userExit) {
            System.out.println("Welcome to the DELIcious!");
            System.out.println("1) Create New Order ");
            System.out.println("0) Exit");

            int input = Utils.getIntegerWithRange("Please choose an option: ", true, 0, 1);
            switch (input) {
                case 1 -> {
                    Store store = new Store();
                    store.prepare();
                    Order order = store.createNewOrder();
                    order.init();
                }
                case 0 -> userExit = true;
            }
        }
        System.out.println("Bye!");
    }
}
