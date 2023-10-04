import CreativityGoodType.CreativityGood;
import java.util.Comparator;

public class ComparatorFactory {
    private static class GoodNameComparator implements Comparator<CreativityGood> {
        @Override
        public int compare(CreativityGood o1, CreativityGood o2) {
            return o1.name().compareTo(o2.name());
        }
    }

    public static Comparator<CreativityGood> getGoodNameComparator() {
        return new GoodNameComparator();
    }

    public static Comparator<CreativityGood> getGoodPriceComparator() {
        return ((o1, o2) -> Float.compare(o1.price(), o2.price()));
    }

    public static Comparator<CreativityGood> byTwoComparators
            (Comparator<CreativityGood> a, Comparator<CreativityGood> b) {
        return a.thenComparing(b);
    }
}
