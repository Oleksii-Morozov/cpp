package alerix.dev.util.io.terminal;

import alerix.dev.model.Dinner;
import alerix.dev.model.Pizza;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class TerminalOutput {
    public static void print(String message) {
        System.out.println(message);
    }

    public static void print(Dinner object, String message) {
        System.out.println(message);
        System.out.println(object);
    }

    public static <G> void print(List<G> collection, String message) {
        System.out.println(message);
        collection.forEach(System.out::println);
    }

    // Universal method for printing any Map
//    public static <K, G> void print(Map<K, G> map, String message) {
//        System.out.println(message);
//        map.forEach((key, value) -> System.out.println(key + "\n" + value));
//    }

    // Special method for printing Map<Dinner, Duration>
    public static void printDinnerDurationMap(Map<Dinner, Duration> map, String message) {
        System.out.println(message);
        map.forEach((key, value) -> System.out.println(
                key.name() + "\n" +
                        value.toHours() + " hours " + value.toMinutesPart() + " minutes " + value.toSecondsPart() + " seconds\n"));
    }

    // Special method for printing Map<Pizza, List<Dinner>>
    public static void printPizzaDinnerMap(Map<Pizza, List<Dinner>> map, String message) {
        System.out.println(message);
        map.forEach((key, value) -> System.out.println(
                key.name() + "\n" +
                        value.stream().map(Dinner::name).reduce((a, b) -> a + ", " + b).orElse("") + "\n"));
    }
}
