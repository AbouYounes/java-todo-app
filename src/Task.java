
/**
 * Represents a single TODO task.
 *
 * A Task has:
 * - an id (unique identifier)
 * - a description
 * - a completion status
 */

public class Task {
    private int id;
    private String description;
    private boolean completed;

    /**
     * Creates a new Task.
     *
     * @param id unique task identifier
     * @param description task description
     */
    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.completed = false;
    }

    /**
     * Returns the task ID.
     *
     * @return task id
     */
    public int getId() {
        return id;
    }

    /**
     * Checks if the task is completed.
     *
     * @return true if completed, false otherwise
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Marks the task as completed.
     */
    public void markCompleted() {
        this.completed = true;
    }

    /**
     * Returns a human-readable string representation of the task.
     *
     * @return formatted task string
     */
    @Override
    public String toString() {
        return id + ". " + description +
                (completed ? " [DONE]" : " [TODO]");
    }

}
