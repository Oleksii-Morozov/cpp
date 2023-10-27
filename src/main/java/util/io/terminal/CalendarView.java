package util.io.terminal;

import model.Project;
import model.Task;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class CalendarView {
    public static void calendarView(Project project) {
        List<Task> sortedTasks = project.tasks().stream()
                .sorted(Comparator.comparing(task -> task.getDeadline().date()))
                .toList();

        LocalDate currentDate = null;
        for (Task task : sortedTasks) {
            if (!task.getDeadline().date().equals(currentDate)) {
                currentDate = task.getDeadline().date();
                System.out.println("\nDate: " + currentDate);
            }
            System.out.println(task);
        }
    }
}
