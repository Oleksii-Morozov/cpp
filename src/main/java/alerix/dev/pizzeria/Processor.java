package alerix.dev.pizzeria;

import alerix.dev.model.Dinner;
import alerix.dev.model.Pizza;
import alerix.dev.model.Pizzeria;
import alerix.dev.util.io.file.Deserializer;
import alerix.dev.util.io.file.Serializer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Processor {
    private final Pizzeria pizzeria;

    public Processor(String dinnerPath, String menuPath, boolean isJson) {
        if(isJson) {
            dinnerPath += ".json";
            menuPath += ".json";
        } else {
            dinnerPath += ".ser";
            menuPath += ".ser";
        }
        pizzeria = new Pizzeria(Deserializer.deserialize(dinnerPath, Dinner.class, isJson),
                Deserializer.deserialize(menuPath, Pizza.class, isJson));
    }

    public List<Dinner> getDinnerList() {
        return pizzeria.dinnerList();
    }

    public List<Pizza> getMenu() {
        return pizzeria.menu();
    }

    public List<Dinner> getSortedByTimeDinnerList() {
        return pizzeria.dinnerList().stream().sorted(Comparator.comparing(Dinner::deliveryDateTime)).toList();
    }

    public List<Dinner> getSortedByPriceDinnerList() {
        return pizzeria.dinnerList().stream().sorted(Comparator.comparing(Dinner::getTotalPrice)).toList();
    }

    public List<Dinner> getDinnersByPizzaName(String pizzaName) {
        return pizzeria.dinnerList().stream().filter
                (dinner -> dinner.orderedPizzas().stream().anyMatch(pizza -> pizza.name().equals(pizzaName))).toList();
    }

    public List<Dinner> getDinnersTharHavingMorePizzaThen(int pizzaCount) {
        return pizzeria.dinnerList().stream().filter(dinner -> dinner.orderedPizzas().size() > pizzaCount).toList();
    }

    public Dinner getDinnerWithBiggestOrder() {
        return pizzeria.dinnerList().stream().max(Comparator.comparing(Dinner::getTotalPrice)).get();
    }

    public Map<Pizza, List<Dinner>> getGroupByPizzaMap() {
        return pizzeria.dinnerList().stream().
                flatMap(dinner -> dinner.orderedPizzas().stream().
                        map(pizza -> Map.entry(pizza, dinner))).
                collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    public Map<Dinner, Duration> getExpiresDinnersMap() {
        return pizzeria.dinnerList().stream().collect(Collectors.toMap(dinner -> dinner,
                dinner -> Duration.between(dinner.deliveryDateTime(), LocalDateTime.now())));
    }

    public List<Dinner> getDinnersWithVeganPizza() {
        return pizzeria.dinnerList().stream().filter(Dinner::isVegan).toList();
    }

    public void serializeCollection() {
        Serializer.serialize(pizzeria.dinnerList(), "results/dinners.ser", true);
        Serializer.serialize(pizzeria.menu(), "results/pizza.ser", true);
    }
}
