import java.time.LocalDateTime;
import java.util.Scanner;

public class Order {
   String orderId;
   List <Meal> items;
   LocalDateTime timeStamp;
   static Customer customer;

    static Scanner scanner = new Scanner(System.in);

    public static void init() {
        createCustomer();
        displayOrderScreen();
    }

    public static void createCustomer() {
        String name;
        String phone;

        System.out.println("Enter your name: ");
        name = scanner.nextLine();

        customer = new Customer(name);
    }

    public static void displayOrderScreen() {
        System.out.println("\n=== Order Menu ===");
        System.out.println("1) Add Sandwich");
        System.out.println("2) Add Drink");
        System.out.println("3) Add Chips");
        System.out.println("4) Checkout");
        System.out.println("0) Cancel Order");
        System.out.print("Choose an option: ");

        int userChoice = Integer.parseInt(scanner.nextLine());


    }
}
