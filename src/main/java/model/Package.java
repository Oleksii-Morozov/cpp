package model;

import java.util.List;

public record Package(Integer id, Integer length, Integer width, Integer height, List<Plank> planks) {
    public void addPlank(Plank plank) {
        planks.add(plank);
    }

    @Override
    public String toString() {
        return "Package[id=" + id + ", length=" + length + ", width=" + width + ", height=" + height + ", planks=" + planks + "\n\t]";
    }
}
