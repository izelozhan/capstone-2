package Utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Utils {
    public static Scanner scanner = new Scanner(System.in);
    public static String RESET = "\u001B[0m";
    public static String RED = "\u001B[31m";
    public static String GREEN = "\u001B[32m";
    public static String YELLOW = "\u001B[33m";
    public static String BLUE = "\u001B[34m";
    public static String CYAN = "\u001B[36m";

    public static void printTitle(String title) {
        System.out.println(BLUE + "\n=== " + title + " ===" + RESET);
    }

    public static void printMenuOption(String option) {
        System.out.println(option);
    }


    public static String getStringFromTerminal(String message) {
        if (!message.isEmpty()) {
            printInfo(message);
        }
        return scanner.nextLine();
    }

    public static String getDoubleFromTerminal(String message, boolean isRequired) {
        if (!message.isEmpty()) {
            printInfo(message);
        }
        while (true) {
            try {
                String input = scanner.nextLine();
                Double.parseDouble(input);
                return input;
            } catch (Exception e) {
                if (!isRequired) {
                    return "";
                } else {
                    printError("Invalid value, please enter again.");
                }
            }
        }
    }

    public static String getDateFromTerminal(String message, boolean isRequired) {
        if (!message.isEmpty()) {
            printInfo(message);
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            try {
                String input = scanner.nextLine();
                LocalDate.parse(input, dateFormatter);
                return input;
            } catch (Exception e) {
                if (!isRequired) {
                    return "";
                } else {
                    printError("Invalid value, please enter again.");
                }
            }
        }
    }

    public static Integer getIntegerFromTerminal(String message, boolean isRequired) {
        if (!message.isEmpty()) {
            printInfo(message);
        }
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (Exception e) {
                if (!isRequired) {
                    return null;
                } else {
                    printError("Invalid value, please enter again.");
                }
            }
        }
    }

    public static void printError(String message) {
        System.out.println(RED + message + RESET);
    }

    public static void printSuccess(String message) {
        System.out.println(GREEN + message + RESET);
    }

    public static void printInfo(String message) {
        System.out.println(YELLOW + message + RESET);
    }

}
