import dev.TextProcessor;
import util.io.FileReader;

import java.util.Scanner;

public class Manager {

    private final TextProcessor textProcessor = new TextProcessor();

    public void run() {
        System.out.println(textProcessor.extractUniqueWordsOfGivenLength(
                FileReader.read("./src/main/resources/Hobbit.txt", 101),
                enterLength()));
    }

    private int enterLength() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter length of words to find: ");
        int length = scanner.nextInt();
        scanner.close();
        return length;
    }

}
