import java.util.Scanner;

/**
 * Entry point of the TODO application.
 *
 * This class handles user interaction through the console
 * and delegates task operations to TodoService.
 */

public class TodoApp {
    public static void main(String[] args) {
        // Create the service responsible for task management
        TodoService service = new TodoService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            // Display menu
            System.out.println("\n=== TODO MENU ===");
            System.out.println("1. Add task");
            System.out.println("2. View tasks");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    service.addTask(description);
                    System.out.println("Task added.");


                    System.out.println("\n--- List tasks ---");
                    for (Task task : service.getTasks()) {
                        System.out.println(task);
                    }
                    break;

                case 2:
                    if (service.getTasks().isEmpty()) {
                        System.out.println("No tasks available.");
                    } else {
                        System.out.println("\n--- List tasks ---");
                        for (Task task : service.getTasks()) {
                            System.out.println(task);
                        }
                    }
                    break;

                case 3:
                    running = false;
                    System.out.println("Exiting TODO App...");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

    }
}