package util.io.terminal;

import java.util.*;

public class TerminalWriterUtility {
    public static <T> void write(List<T> items) {
        for (T item : items) {
            System.out.println(item.toString());
        }
        System.out.println();
    }
}
