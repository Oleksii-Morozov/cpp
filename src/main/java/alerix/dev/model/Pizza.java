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

        StringBuilder toppingsString = new StringBuilder();
        int itemsPerLine = 2;
        for (int i = 0; i < toppings.size(); i++) {
            toppingsString.append(toppings.get(i));

            // Add a comma if it's not the last item
            if (i < toppings.size() - 1) {
                toppingsString.append(", ");
            }

            // Break the line after every two items, but not at the end
            if ((i + 1) % itemsPerLine == 0 && i < toppings.size() - 1) {
                toppingsString.append("\n\t");
            }
        }

        String veganString = isVegan ? "vegan" : "not vegan";
        String formattedName = String.format("%" + (20 + name.length() / 2) + "s", name);
        return String.format("%s\n\t%s\n\t%.0fg - $%.2f\n\t%s", formattedName, toppingsString,  weight, price, veganString);
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
