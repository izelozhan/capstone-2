import java.util.Scanner;

public class DELIcious {

//    public static void prepare() {
//        Drink drink = new Drink("Cola", new SizePrice[] {
//                new SizePrice(new Size("Small"), 12),
//                new SizePrice(new Size("Medium"), 12),
//                new SizePrice(new Size("Large"), 12),
//        });
//
//        Topping steak = new Topping("Steak", new SizePrice[] {
//                new SizePrice(new Size("4'"), 1),
//                new SizePrice(new Size("8'"), 2),
//                new SizePrice(new Size("12'"), 3),
//        });
//
//        Drink drink = new Drink("Ayran", new SizePrice[] {
//                new SizePrice(new Size("Small"), 12),
//                new SizePrice(new Size("Small"), 32),
//                new SizePrice(new Size("Small"), 22),
//        });
//
//        Topping tomato = new Topping("Tomato", new SizePrice[]);
//
//        HashMap<Drink, ArrayList<SizePrice>> drinkMapping = new HashMap<>();
//
//        SizePrice smallPrice = new SizePrice(new Size("Small"), 12);
//        SizePrice mediumPrice = new SizePrice(new Size("Medium"), 14);
//        SizePrice largePrice = new SizePrice(new Size("Large"), 16);
//        String[] drinkTypes = new String[] { "Cola", "Fanta", "Ayran" };
//        for(String type : drinkTypes) {
//            Drink drink = new Drink(type);
//
//            drinkMapping.put(drink, new ArrayList<>());
//            drinkMapping.get(drink).put(smallPrice);
//            drinkMapping.get(drink).put(mediumPrice);
//            drinkMapping.get(drink).put(largePrice);
//        }
//
//        Drink foundCola = drinkMapping.keySet().stream().findFirst(i -> i.name == "Cola");
//        drinkMapping[foundCola]
//
//        // cola - small - 12
//        // cola - medium - fiyat
//
//
//
//
//
//
//
//
//    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning){
            System.out.println("Welcome to the DELIcious!");
            System.out.println("1) Create New Order ");
            System.out.println("0) Exit");

            int userInput = Integer.parseInt(scanner.nextLine());

            switch (userInput){
                case 1 -> Order.init();
                case 0 -> {
                    System.out.println("Thank you! Goodbye!");
                    isRunning = false;
                }
                default -> System.out.println("Invalid input. Please try again.");
            }

        }

    }
}


