package alerix.dev.model;

import java.util.List;

public record Pizzeria(List<Dinner> dinnerList, List<Pizza> menu) {
}
