import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for managing TODO tasks.
 *
 * This class contains business logic such as:
 * - adding tasks
 * - storing tasks in memory
 * - retrieving tasks
 */
public class TodoService {

    /** List that stores all tasks in memory */
    private List<Task> tasks = new ArrayList<>();

    /** Counter used to generate unique task IDs */
    private int nextId = 1;

    /** File where tasks are stored */
    private static final String FILE_NAME = "tasks.txt";

    /**
     * Adds a new task with the given description.
     *
     * @param description description of the task
     */
    public void addTask(String description) {
        tasks.add(new Task(nextId++, description));
    }

    /**
     * Returns all tasks.
     *
     * @return list of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Marks a task as completed using its ID.
     *
     * @param id the unique identifier of the task
     * @return true if task was found and completed, false otherwise
     */
    public boolean completeTask(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.markCompleted();
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes a task by its ID.
     *
     * @param id the unique identifier of the task
     * @return true if task was found and deleted, false otherwise
     */
    public boolean deleteTask(int id) {
        return tasks.removeIf(task -> task.getId() == id);
    }

    /**
     * Loads tasks from the file into memory.
     */
    public void loadTasks() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return; // No file yet → nothing to load
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                int id = Integer.parseInt(parts[0]);
                String description = parts[1];
                boolean completed = Boolean.parseBoolean(parts[2]);

                Task task = new Task(id, description);
                if (completed) {
                    task.markCompleted();
                }

                tasks.add(task);
                nextId = Math.max(nextId, id + 1);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    /**
     * Saves all tasks to the file.
     */
    public void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }



}
