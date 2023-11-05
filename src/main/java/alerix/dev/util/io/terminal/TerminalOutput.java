package alerix.dev.util.io.terminal;

import java.util.List;
import java.util.Map;

public class TerminalOutput {
    public static void print(String message) {
        System.out.println(message);
    }

    public static <G> void print(G object, String message) {
        System.out.println(message);
        System.out.println(object);
    }

    public static <G> void print(List<G> collection, String message) {
        System.out.println(message);
        collection.forEach(System.out::println);
    }

    public static <K, G> void print(Map<K, G> map, String message) {
        System.out.println(message);
        map.forEach((key, value) -> System.out.println(key + " - " + value));
    }
}
