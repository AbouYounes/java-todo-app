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
            System.out.println("3. Complete task");
            System.out.println("4. Delete task");
            System.out.println("5. Exit");
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
                    if (service.getTasks().isEmpty()) {
                        System.out.println("No tasks to complete.");
                        System.out.println("\n--- List tasks ---");
                        for (Task task : service.getTasks()) {
                            System.out.println(task);
                        }
                        break;
                    }

                    System.out.print("Enter task ID to complete: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    boolean completed = service.completeTask(id);
                    if (completed) {
                        System.out.println("Task marked as completed.");
                    } else {
                        System.out.println("Task not found.");
                    }
                    System.out.println("\n--- List tasks ---");
                    for (Task task : service.getTasks()) {
                        System.out.println(task);
                    }
                    break;

                case 4:
                    if (service.getTasks().isEmpty()) {
                        System.out.println("No tasks to delete.");
                        System.out.println("\n--- List tasks ---");
                        for (Task task : service.getTasks()) {
                            System.out.println(task);
                        }
                        break;
                    }

                    System.out.print("Enter task ID to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();

                    boolean deleted = service.deleteTask(deleteId);
                    if (deleted) {
                        System.out.println("Task deleted.");
                        System.out.println("\n--- List tasks ---");
                        for (Task task : service.getTasks()) {
                            System.out.println(task);
                        }
                    } else {
                        System.out.println("Task not found.");
                        System.out.println("\n--- List tasks ---");
                        for (Task task : service.getTasks()) {
                            System.out.println(task);
                        }
                    }
                    break;

                case 5:
                    running = false;
                    System.out.println("Exiting TODO App...");
                    break;



                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();

    }
}