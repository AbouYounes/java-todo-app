package service;

import model.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for TodoService.
 */
public class TodoServiceTest {

    @Test
    void addTask_shouldIncreaseTaskListSize() {
        TodoService service = new TodoService();

        service.addTask("Test task");

        assertEquals(1, service.getTasks().size());
    }

    @Test
    void completeTask_shouldMarkTaskAsCompleted() {
        TodoService service = new TodoService();
        service.addTask("Test task");

        boolean result = service.completeTask(1);

        assertTrue(result);
        assertTrue(service.getTasks().get(0).isCompleted());
    }

    @Test
    void deleteTask_shouldRemoveTask() {
        TodoService service = new TodoService();
        service.addTask("Test task");

        boolean result = service.deleteTask(1);

        assertTrue(result);
        assertEquals(0, service.getTasks().size());
    }
}
