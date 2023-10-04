import CreativityGoodType.CreativityGood;
import lombok.*;
import java.util.*;

@Getter
public class CreativityGoodsManager {
    private CreativityGood[] goods;

    public CreativityGoodsManager(int count) {
        this.goods = CreativityGoodsGenerator.generate(count);
    }

    public void sortGoods(int[] sortingChoices, boolean reverse) {
        Arrays.sort(goods, getComparator(sortingChoices, reverse));
    }

    public CreativityGood[] findGoodsByType(String[] availableTypes , Set<Integer> selectedIndicates) {
        return Arrays.stream(goods)
                .filter(good -> selectedIndicates
                        .contains(Arrays.asList(availableTypes)
                                .indexOf(good.type().typeName()) + 1))
                .toArray(CreativityGood[]::new);
    }

    private Comparator<CreativityGood> getComparator(int[] sortingChoices, boolean reverse) {
        Comparator<CreativityGood> result = null;
        Comparator<CreativityGood> tempComp;
        for(int choise : sortingChoices) {
            tempComp = null;
            tempComp = switch (choise) {
                case 1 -> ComparatorFactory.getGoodNameComparator();
                case 2 -> ComparatorFactory.getGoodPriceComparator();
                default -> tempComp;
            };
            if(result != null) {
                result = ComparatorFactory.byTwoComparators(result, tempComp);
            } else {
                result = tempComp;
            }
        }
        if(reverse) {
            assert result != null;
            result = result.reversed();
        }
        return result;
    }
}
