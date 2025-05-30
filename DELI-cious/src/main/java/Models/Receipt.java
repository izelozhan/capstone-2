package Models;

import java.io.*;
import java.time.format.DateTimeFormatter;

public class Receipt {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    public static void writeToFile(Order order) {
        String summaryString = order.getSummary();
        try {
            String directory = "Receipts";
            File receiptsDir = new File(directory);
            if (!receiptsDir.exists()) {
                receiptsDir.mkdirs(); //create
            }
            String name = formatter.format(order.timeStamp) + ".txt";
            String fileName = directory + File.separator + name;

            PrintStream fileOut = new PrintStream(new FileOutputStream(fileName));
            fileOut.println(summaryString);
            fileOut.close();
            System.out.println("Written to file: " + fileName);
        } catch (Exception ex) {
            System.out.println("An error happened while saving the file.");
        }

    }


}
