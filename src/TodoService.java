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


}
