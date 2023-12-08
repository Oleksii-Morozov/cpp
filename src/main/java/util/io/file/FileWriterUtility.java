package util.io.file;

import java.io.*;
import java.util.*;

public class FileWriterUtility {
    public static <T> void write(String filename, List<T> items, boolean append) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, append))) {
            for (T item : items) {
                writer.write(item.toString());
                if(!append) writer.newLine();
            }
            writer.newLine();
        }
    }

    public static <T> void write(String filename, T item, boolean append) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, append))) {
            writer.write(item.toString());
            writer.newLine();
        }
    }
}
