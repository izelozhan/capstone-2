import Models.Order;
import Models.Store;
import Screens.OrderScreen;
import Utilities.Utils;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        boolean userExit = false;
        Store store = new Store();

        while (!userExit) {

            Utils.printTitle("🥪 Welcome to DELIcious Sandwich Shop! 🥪");
            System.out.println("\nWhat would you like to do today?");
            System.out.println("""
                    1) Start a New Order
                    0) Exit the Shop""");

            int input = Utils.getIntegerWithRange("Please choose an option (0 or 1): ", true, 0, 1);
            switch (input) {
                case 1 -> {
                    Order order = store.createNewOrder();
                   OrderScreen screen = new OrderScreen(order, store);
                    screen.displayWelcomeScreen();
                }
                case 0 -> userExit = true;
            }
        }
        Utils.printExitMessage("👋 Thanks for stopping by DELIcious. See you next time!");
    }
}
