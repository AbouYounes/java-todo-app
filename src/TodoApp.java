
/**
 * Entry point of the TODO application.
 *
 * This class connects the application logic (TodoService)
 * and displays tasks in the console.
 */

public class TodoApp {
    public static void main(String[] args) {
        // Create the service responsible for task management
        TodoService service = new TodoService();

        // Add sample tasks
        service.addTask("Learn Java basics");
        service.addTask("Build TODO application");

        // Display all tasks
        for (Task task : service.getTasks()) {
            System.out.println(task);
        }

    }
}