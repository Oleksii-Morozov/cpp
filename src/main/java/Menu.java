import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private final TaskManager taskManager = new TaskManager();
    private final Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nTask Manager Menu:");
            System.out.println("1. Create a project");
            System.out.println("2. List all projects");
            System.out.println("3. Remove a project");
            System.out.println("4. Manage tasks in a project");
            System.out.println("5. Print calendar view for a project");
            System.out.println("6. Save projects to a file");
            System.out.println("7. Load projects from a file");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        taskManager.createProject(scanner);
                        break;
                    case 2:
                        taskManager.printProjects();
                        break;
                    case 3:
                        taskManager.removeProject(scanner);
                        break;
                    case 4:
                        manageTasks();
                        break;
                    case 5:
                        taskManager.printCalendarView(scanner);
                        break;
                    case 6:
                        taskManager.saveProjectsToFile(scanner);
                        break;
                    case 7:
                        taskManager.loadProjectsFromFile(scanner);
                        break;
                    case 8:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.next(); // consume the invalid input
            }
        }

        System.out.println("Thank you for using Task Manager. Goodbye!");
    }

    private void manageTasks() {
        System.out.println("\nTask Management:");
        System.out.println("1. Create a task");
        System.out.println("2. List tasks by project");
        System.out.println("3. Remove a task");
        System.out.println("4. Create a subtask");
        System.out.println("5. List subtasks by task");
        System.out.println("6. Remove a subtask");
        System.out.println("7. Mark task as done");
        System.out.println("8. Mark subtask as done");
        System.out.println("9. Print priority tasks");
        System.out.println("10. Return to main menu");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                taskManager.createTask(scanner);
                break;
            case 2:
                taskManager.printTasksByProject(scanner);
                break;
            case 3:
                taskManager.removeTask(scanner);
                break;
            case 4:
                taskManager.createSubtask(scanner);
                break;
            case 5:
                taskManager.printSubtasksByTask(scanner);
                break;
            case 6:
                taskManager.removeSubtask(scanner);
                break;
            case 7:
                taskManager.markTaskAsDone(scanner);
                break;
            case 8:
                taskManager.markSubtaskAsDone(scanner);
                break;
            case 9:
                taskManager.listPriorityTasks();
                break;
            case 10:
                break; // simply return to the main menu
            default:
                System.out.println("Invalid choice. Please select a valid option.");
                break;
        }
    }
}
