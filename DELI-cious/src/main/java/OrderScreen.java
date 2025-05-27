import java.util.Scanner;

public class OrderScreen {
    static Scanner scanner = new Scanner(System.in);
    private OrderScreen currentOrder;
    static Customer customer;


    public static void init() {
        createCustomer();
        displayOrderScreen();
    }
 #
    public static void createCustomer() {
        String name;
        String phone;

        System.out.println("Enter your name: ");
        name = scanner.nextLine();
        System.out.println("Enter your phone: ");
        phone = scanner.nextLine();

        customer = new Customer(name, phone);
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

        switch (userChoice) {
            case 1:
                System.out.println("Welcome to our sandwich creator! Are you ready to create delicious sandwich? Let's go! \n");

        }

    }
}
