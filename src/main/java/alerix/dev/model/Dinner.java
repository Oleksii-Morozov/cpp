package alerix.dev.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record Dinner(
        String name,
        List<Pizza> orderedPizzas,
        String address,
        LocalDateTime deliveryDateTime) implements Serializable {
    public Dinner {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (orderedPizzas == null || orderedPizzas.isEmpty()) {
            throw new IllegalArgumentException("Ordered pizzas cannot be null or empty");
        }
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address cannot be null or blank");
        }
    }

    public double getTotalPrice() {
        return orderedPizzas.stream().mapToDouble(Pizza::price).sum();
    }

    public boolean isVegan() {
        return orderedPizzas.stream().anyMatch(Pizza::isVegan);
    }

    @Override
    public String toString() {
        String orderString = orderedPizzas.stream().map(Pizza::name).reduce((a, b) -> a + ", " + b).orElse("");
        return String.format("%-30s | %-50s | $%.2f | %-20s | %s", name, orderString, getTotalPrice(), address,
                deliveryDateTime.format(DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy"))
        );
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Dinner dinner) {
            return this.name.equals(dinner.name) &&
                    this.orderedPizzas.equals(dinner.orderedPizzas) &&
                    this.address.equals(dinner.address) &&
                    this.deliveryDateTime.equals(dinner.deliveryDateTime);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + orderedPizzas.hashCode() + address.hashCode() + deliveryDateTime.hashCode();
    }
}
