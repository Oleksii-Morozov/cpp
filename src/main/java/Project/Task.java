package Project;

import java.util.List;

public record Task(
        String title,
        String description,
        Priority priority,
        String status,
        Deadline deadline,
        List<Subtask> subtasks) {
    @Override
    public String toString() {
        StringBuilder subtaskString = null;
        for (Subtask subtask : subtasks) {
            subtaskString = (subtaskString == null ? new StringBuilder() : subtaskString).append(subtask);
        }
        return title + "\n" + description +
               "\nPriority: " + priority +
               "\nStatus: " + status +
               "\nDeadline: " + deadline +
               "\n" + subtaskString;
    }
}
