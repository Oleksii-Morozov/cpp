package CreativityGoodType;


public record CreativityGood(int id, String name, GoodType type, UsageArea usageArea, float price) {
    @Override
    public String toString() {
        return String.format("%-5d | %-30s | %-30s | %-15s | %-5.2f"
                , id, name, type.typeName(), usageArea.name(), price);
    }
}
