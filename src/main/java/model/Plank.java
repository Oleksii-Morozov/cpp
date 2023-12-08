package model;

public record Plank(Integer id, Integer length, Integer width, Integer height) {
    @Override
    public String toString() {
        return "\n\tPlank[id=" + id + ", length=" + length + ", width=" + width + ", height=" + height + "]";
    }

}
