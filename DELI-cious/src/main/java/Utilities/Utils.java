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
    public static String BLACK = "\u001B[30m";
    public static String UNDERLINE = "\u001B[4m";
    public static String BG_CYAN = "\u001B[46m";
    public static String BOLD = "\u001B[1m";

    public static void printTitle(String title) {
        System.out.println(UNDERLINE + BOLD + BLUE + title + RESET);
    }

    public static void printOrderSubtitles(String subtitle) {
        System.out.println(GREEN + BOLD + subtitle + RESET);
    }

    public static void printMenuOption(String option) {
        System.out.println(option);
    }

    public static void printGetUserOption(String message) {
        System.out.println(CYAN + "=> " + message + RESET);
    }

    public static void printExistTopping(String message){
        System.out.println(GREEN + message + RESET);
    }


    public static String getStringFromTerminal(String message) {
        if (!message.isEmpty()) {
            printGetUserOption(message);
        }
        return scanner.nextLine();
    }

    public static int getIntegerFromTerminal(String message, boolean isRequired) {
        if (!message.isEmpty()) {
            printGetUserOption(message);
        }
        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    if (!isRequired) {
                        return -1;
                    } else {
                        printError("This field is required.");
                        continue;
                    }
                }
                return Integer.parseInt(input);
            } catch (Exception e) {
                printError("Invalid value, please enter again.");
            }
        }
    }

    public static int getIntegerWithRange(String message, boolean isRequired, int min, int max) {
        if (!message.isEmpty()) {
            printGetUserOption(message);
        }
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    if (!isRequired) {
                        return -1;
                    } else {
                        printError("This field is required.");
                        continue;
                    }
                }
                int num = Integer.parseInt(input);
                if (num >= min && num <= max) {
                    return num;
                } else {
                    printError("Please enter a number between " + min + " - " + max);
                }
            } catch (Exception e) {
                printError("Invalid value, please enter again.");
            }
        }
    }


    public static void printError(String message) {
        System.out.println(RED + message + RESET);
    }

    public static void printUnderline(String message) {
        System.out.println(UNDERLINE + message + RESET);
    }

    public static void printExitMessage(String message) {
        System.out.println(BG_CYAN + BLACK + message + RESET);
    }


}
