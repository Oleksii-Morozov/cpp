package util.io;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReader {
    public static String read(String filepath, int amountOfLines) {
        if(filepath == null || filepath.isEmpty()) {
            throw new IllegalArgumentException("Filepath cannot be null or empty");
        }

        StringBuilder text = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filepath))) {
            String line = reader.readLine();
            for (int i = 0; line != null && i < amountOfLines; i++) {
                text.append(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error reading from file");
        }
        return text.toString();
    }
}
