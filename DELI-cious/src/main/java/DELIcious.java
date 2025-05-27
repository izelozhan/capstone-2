import java.util.Scanner;

public class DELIcious {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning){
            System.out.println("Welcome to the DELIcious!");
            System.out.println("1) Create New Order ");
            System.out.println("0) Exit");

            int userInput = Integer.parseInt(scanner.nextLine());

            switch (userInput){
                case 1 -> OrderScreen.init();
                case 0 -> {
                    System.out.println("Thank you! Goodbye!");
                    isRunning = false;
                }
                default -> System.out.println("Invalid input. Please try again.");
            }

        }

    }
}


