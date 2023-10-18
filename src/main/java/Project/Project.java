package Project;

import java.util.List;

public record Project(String title, String description, List<Task> tasks) {
    @Override
    public String toString() {
        StringBuilder taskString = null;
        for (Task task : tasks) {
            taskString = (taskString == null ? new StringBuilder() : taskString).append(task);
        }
        return title + "\n" + description + "\n" + taskString;
    }
}
