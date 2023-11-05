package alerix.dev.model;
import lombok.Getter;

import java.util.List;


@Getter
public record Pizzeria(List<Dinner> dinnerList, List<Pizza> menu) {
}
