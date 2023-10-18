package Project;

public record Subtask(
        String title,
        String description,
        Priority priority,
        String status,
        Deadline deadline) {

    @Override
    public String toString() {
        return title + "\n" + description +
               "\nPriority: " + priority +
               "\nStatus: " + status +
               "\nDeadline: " + deadline;
    }
}
