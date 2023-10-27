package model;

import java.io.Serializable;
import java.util.List;

public record Project(Integer id, String title, String description, List<Task> tasks) implements Serializable {
    @Override
    public String toString() {
        return String.format("%d | %s | %s", id, title, description);
    }
}
