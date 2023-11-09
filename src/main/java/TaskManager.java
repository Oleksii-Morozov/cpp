import model.Deadline;
import model.Project;
import model.Subtask;
import model.Task;
import util.io.file.Reader;
import util.io.file.Writer;
import util.io.terminal.CalendarView;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {

    List<Project> projects = new ArrayList<>();

    // Helper function to find a project by its ID
    private Project findProjectById(int id) {
        for (Project project : projects) {
            if (project.id().equals(id)) {
                return project;
            }
        }
        return null;
    }

    // Helper function to find a task in a project by its ID
    private Task findTaskInProjectById(Project project, int taskId) {
        if (project == null) return null;

        for (Task task : project.tasks()) {
            if (task.getId().equals(taskId)) {
                return task;
            }
        }
        return null;
    }

    private Subtask findSubtaskInTaskById(Task task, int subtaskId) {
        if (task == null) return null;

        for (Subtask subtask : task.getSubtasks()) {
            if (subtask.getId().equals(subtaskId)) {
                return subtask;
            }
        }
        return null;
    }

    public void createProject(Scanner scanner) {
        System.out.println("Enter project title:");
        String title = scanner.nextLine();

        System.out.println("Enter project description:");
        String description = scanner.nextLine();

        // Assuming IDs are auto-generated based on the size of the projects list
        int id = projects.size() + 1;
        projects.add(new Project(id, title, description, new ArrayList<>()));
    }

    public void printProjects() {
        System.out.println("Projects:");
        for (Project project : projects) {
            System.out.println(project);
        }
    }

    public void removeProject(Scanner scanner) {
        System.out.println("Enter the ID of the project to remove:");
        int id = Integer.parseInt(scanner.nextLine());

        Project projectToRemove = findProjectById(id);
        if (projectToRemove != null) {
            projects.remove(projectToRemove);
            System.out.println("Project removed successfully.");
        } else {
            System.out.println("Project with ID " + id + " not found.");
        }
    }

    public void createTask(Scanner scanner) {
        System.out.println("Enter the ID of the project for this task:");
        int projectId = Integer.parseInt(scanner.nextLine());
        Project project = findProjectById(projectId);

        if (project == null) {
            System.out.println("Project not found.");
            return;
        }

        System.out.println("Enter task title:");
        String title = scanner.nextLine();

        System.out.println("Enter task description:");
        String description = scanner.nextLine();

        System.out.println("Enter deadline date (YYYY-MM-DD):");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        System.out.println("Enter deadline time (HH:MM):");
        LocalTime time = LocalTime.parse(scanner.nextLine());

        System.out.println("Is this task a priority? (yes/no):");
        boolean isPriority = scanner.nextLine().equalsIgnoreCase("y");

        int id = project.tasks().size() + 1;
        Task newTask = new Task(id, title, description, new Deadline(date, time), isPriority, false, new ArrayList<>());
        project.tasks().add(newTask);
    }

    public void printTasksByProject(Scanner scanner) {
        System.out.println("Enter the ID of the project to view tasks:");
        int projectId = Integer.parseInt(scanner.nextLine());
        Project project = findProjectById(projectId);

        if (project == null) {
            System.out.println("Project not found.");
            return;
        }

        System.out.println("Tasks in " + project.title() + ":");
        for (Task task : project.tasks()) {
            System.out.println(task);
        }
    }

    public void removeTask(Scanner scanner) {
        System.out.println("Enter the ID of the project containing the task to remove:");
        int projectId = Integer.parseInt(scanner.nextLine());
        Project project = findProjectById(projectId);

        if (project == null) {
            System.out.println("Project not found.");
            return;
        }

        System.out.println("Enter the ID of the task to remove:");
        int taskId = Integer.parseInt(scanner.nextLine());
        Task taskToRemove = findTaskInProjectById(project, taskId);

        if (taskToRemove != null) {
            project.tasks().remove(taskToRemove);
            System.out.println("Task removed successfully.");
        } else {
            System.out.println("Task with ID " + taskId + " not found in the specified project.");
        }
    }

    public void createSubtask(Scanner scanner) {
        System.out.println("Enter the ID of the project containing the task for this subtask:");
        int projectId = Integer.parseInt(scanner.nextLine());
        Project project = findProjectById(projectId);

        if (project == null) {
            System.out.println("Project not found.");
            return;
        }

        System.out.println("Enter the ID of the task for this subtask:");
        int taskId = Integer.parseInt(scanner.nextLine());
        Task task = findTaskInProjectById(project, taskId);

        if (task == null) {
            System.out.println("Task not found.");
            return;
        }

        System.out.println("Enter subtask title:");
        String title = scanner.nextLine();

        System.out.println("Enter subtask description:");
        String description = scanner.nextLine();

        System.out.println("Enter deadline date (YYYY-MM-DD):");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        System.out.println("Enter deadline time (HH:MM):");
        LocalTime time = LocalTime.parse(scanner.nextLine());

        System.out.println("Is this subtask a priority? (yes/no):");
        boolean isPriority = scanner.nextLine().equalsIgnoreCase("y");

        int id = task.getSubtasks().size() + 1;
        Subtask newSubtask = new Subtask(id, title, description, new Deadline(date, time), isPriority, false);
        task.getSubtasks().add(newSubtask);
    }

    public void printSubtasksByTask(Scanner scanner) {
        System.out.println("Enter the ID of the project containing the task:");
        int projectId = Integer.parseInt(scanner.nextLine());
        Project project = findProjectById(projectId);

        if (project == null) {
            System.out.println("Project not found.");
            return;
        }

        System.out.println("Enter the ID of the task to view subtasks:");
        int taskId = Integer.parseInt(scanner.nextLine());
        Task task = findTaskInProjectById(project, taskId);

        if (task == null) {
            System.out.println("Task not found.");
            return;
        }

        System.out.println("Subtasks in " + task.getTitle() + ":");
        for (Subtask subtask : task.getSubtasks()) {
            System.out.println(subtask);
        }
    }

    public void removeSubtask(Scanner scanner) {
        System.out.println("Enter the ID of the project containing the task with the subtask to remove:");
        int projectId = Integer.parseInt(scanner.nextLine());
        Project project = findProjectById(projectId);

        if (project == null) {
            System.out.println("Project not found.");
            return;
        }

        System.out.println("Enter the ID of the task containing the subtask to remove:");
        int taskId = Integer.parseInt(scanner.nextLine());
        Task task = findTaskInProjectById(project, taskId);

        if (task == null) {
            System.out.println("Task not found.");
            return;
        }

        System.out.println("Enter the ID of the subtask to remove:");
        int subtaskId = Integer.parseInt(scanner.nextLine());
        Subtask subtaskToRemove = findSubtaskInTaskById(task, subtaskId);

        if (subtaskToRemove != null) {
            task.getSubtasks().remove(subtaskToRemove);
            System.out.println("Subtask removed successfully.");
        } else {
            System.out.println("Subtask with ID " + subtaskId + " not found in the specified task.");
        }
    }

    public void printCalendarView(Scanner scanner) {
        System.out.println("Enter the ID of the project to view its calendar:");
        int projectId = Integer.parseInt(scanner.nextLine());
        Project project = findProjectById(projectId);

        if (project == null) {
            System.out.println("Project not found.");
            return;
        }

        CalendarView.calendarView(project);
    }

    public void saveProjectsToFile(Scanner scanner) {
        System.out.println("Enter filename to save projects:");
        String filename = scanner.nextLine();
        try {
            Writer.writeProjectsToFile(projects, filename);
            System.out.println("Projects saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void loadProjectsFromFile(Scanner scanner) {
        System.out.println("Enter filename to load projects:");
        String filename = scanner.nextLine();
        try {
            projects = Reader.readProjectsFromFile(filename);
            System.out.println("Projects loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    public void listPriorityTasks() {
        System.out.println("Priority Tasks:");
        for (Project project : projects) {
            for (Task task : project.tasks()) {
                if (task.getIsPriority()) {
                    System.out.println(task);
                }
            }
        }
    }

    public void markTaskAsDone(Scanner scanner) {
        System.out.println("Enter the ID of the project containing the task:");
        int projectId = Integer.parseInt(scanner.nextLine());
        Project project = findProjectById(projectId);

        if (project == null) {
            System.out.println("Project not found.");
            return;
        }

        System.out.println("Enter the ID of the task to mark as done:");
        int taskId = Integer.parseInt(scanner.nextLine());
        Task task = findTaskInProjectById(project, taskId);

        if (task != null) {
            task.setIsDone();
            System.out.println("Task marked as done successfully.");
        } else {
            System.out.println("Task not found.");
        }
    }

    public void markSubtaskAsDone(Scanner scanner) {
        System.out.println("Enter the ID of the project containing the task with the subtask to mark as done:");
        int projectId = Integer.parseInt(scanner.nextLine());
        Project project = findProjectById(projectId);

        if (project == null) {
            System.out.println("Project not found.");
            return;
        }

        System.out.println("Enter the ID of the task containing the subtask to mark as done:");
        int taskId = Integer.parseInt(scanner.nextLine());
        Task task = findTaskInProjectById(project, taskId);

        if (task == null) {
            System.out.println("Task not found.");
            return;
        }

        System.out.println("Enter the ID of the subtask to mark as done:");
        int subtaskId = Integer.parseInt(scanner.nextLine());
        Subtask subtask = findSubtaskInTaskById(task, subtaskId);

        if (subtask != null) {
            subtask.setIsDone(true);
            System.out.println("Subtask marked as done successfully.");
        } else {
            System.out.println("Subtask not found.");
        }
    }

}
