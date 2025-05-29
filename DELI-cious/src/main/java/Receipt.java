import java.io.*;
import java.time.format.DateTimeFormatter;

public class Receipt {
    Order order;
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
    public Receipt(Order order) {
        this.order = order;
    }

    public void writeToFile(String content) throws IOException {
        String directory = "Receipts";
        File receiptsDir = new File(directory);
        if (!receiptsDir.exists()) {
            receiptsDir.mkdirs(); //create
        }
        String name = formatter.format(this.order.timeStamp) + ".txt";
        String fileName = directory + File.separator + name;

        PrintStream fileOut = new PrintStream(new FileOutputStream(fileName));
        fileOut.println(content);
        fileOut.close();
        System.out.println("Written to file: " + fileName);
    }
}
