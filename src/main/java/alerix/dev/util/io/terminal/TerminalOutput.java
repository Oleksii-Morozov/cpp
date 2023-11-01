package alerix.dev.util.io.terminal;

import java.util.List;

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


}
