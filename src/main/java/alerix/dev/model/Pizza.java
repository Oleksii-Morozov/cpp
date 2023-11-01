package alerix.dev.model;

import java.io.Serializable;
import java.util.List;

public record Pizza(
        String name,
        double weight,
        double price,
        List<String> toppings,
        boolean isVegan) implements Serializable {

    public Pizza {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight cannot be less or equal to zero");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price cannot be less or equal to zero");
        }
        if (toppings == null || toppings.isEmpty()) {
            throw new IllegalArgumentException("Toppings cannot be null or empty");
        }
    }

    @Override
    public String toString() {
        String toppingsString = String.join(", ", toppings);
        String veganString = isVegan ? "vegan" : "not vegan";
        return String.format("%s (%.2fg, $%.2f, %-40s, %s)", name, weight, price, toppingsString, veganString);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pizza pizza) {
            return this.name.equals(pizza.name) &&
                    this.weight == pizza.weight &&
                    this.price == pizza.price &&
                    this.toppings.equals(pizza.toppings) &&
                    this.isVegan == pizza.isVegan;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + (int) weight + (int) price + toppings.hashCode() + (isVegan ? 1 : 0);
    }
}
