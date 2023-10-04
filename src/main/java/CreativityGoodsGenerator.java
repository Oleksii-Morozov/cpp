import CreativityGoodType.*;

import java.util.Random;

public class CreativityGoodsGenerator {
    static final GoodType[] goodTypes = {
            new GoodType(1, "Acrylic Paint", "Used for painting on canvas"),
            new GoodType(2, "Clay", "Used for sculpting statues"),
            new GoodType(3, "Fountain Pen", "Used for writing"),
            new GoodType(4, "Oil Paint", "Rich paint used for canvas and board"),
            new GoodType(5, "Watercolor", "Transparent paint used with water"),
            new GoodType(6, "Sketch Pad", "Used for pencil and charcoal sketches"),
            new GoodType(7, "Charcoal", "Used for black and white art"),
            new GoodType(8, "Pastel", "Soft color sticks used for coloring"),
            new GoodType(9, "Colored Pencil", "Pencils used for coloring"),
            new GoodType(10, "Graphite Pencil", "Standard pencil for drawing"),
            new GoodType(11, "Ink", "Liquid used for writing and drawing"),
            new GoodType(12, "Brush Set", "Various brushes for painting"),
            new GoodType(13, "Easel", "Support for holding canvas"),
            new GoodType(14, "Palette", "Board for mixing paint"),
            new GoodType(15, "Craft Scissors", "Scissors designed for crafting"),
            new GoodType(16, "Knitting Needles", "Tools for knitting"),
            new GoodType(17, "Crochet Hook", "Tool for crocheting"),
            new GoodType(18, "Origami Paper", "Colored paper for folding"),
            new GoodType(19, "Beads", "Small pieces used for jewelry and decoration"),
            new GoodType(20, "Ceramic", "Material used for pottery and sculpting"),

    };

    private static final UsageArea[] usageAreas = {
            new UsageArea(1, "Painting"),
            new UsageArea(2, "Drawing"),
            new UsageArea(3, "Sculpting"),
            new UsageArea(4, "Writing"),
            new UsageArea(5, "Knitting"),
            new UsageArea(6, "Crocheting"),
            new UsageArea(7, "Origami"),
            new UsageArea(8, "Jewelry"),
            new UsageArea(9, "Decoration"),
    };

    private static final String[] adjectives = {
        "Deluxe",
        "Pro",
        "Beginner's",
        "Master's",
        "Vintage",
        "Eco-friendly",
        "All-purpose",
        "Multimedia",
        "Waterproof",
        "Child-safe"
    };

    public static CreativityGood[] generate(int numberOfGoods) {
        CreativityGood[] goods = new CreativityGood[numberOfGoods];
        Random random = new Random();

        for(int i = 0; i < numberOfGoods; i++) {
            int randomTypeIndex = random.nextInt(goodTypes.length);
            int randomUsageAreaIndex = random.nextInt(usageAreas.length);
            int randomAdjectiveIndex = random.nextInt(adjectives.length);

            goods[i] = new CreativityGood(
                    i+1,
                    adjectives[randomAdjectiveIndex] + " " + goodTypes[randomTypeIndex].typeName(),
                    goodTypes[randomTypeIndex],
                    usageAreas[randomUsageAreaIndex], Float
                    .parseFloat(String.format("%.2f", random.nextFloat() * 100)));
        }

        return goods;
    }
}
