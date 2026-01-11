package ui;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Task;
import service.TodoService;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


/**
 * Controller for the JavaFX TODO application.
 * Handles user actions and connects UI with business logic.
 */
public class TodoController {

    private final TodoService service;
    private final ListView<Task> taskListView;
    private final TextField taskInput;

    public TodoController(TodoService service,
                          ListView<Task> taskListView,
                          TextField taskInput,
                          Button addButton,
                          Button completeButton,
                          Button deleteButton) {

        this.service = service;
        this.taskListView = taskListView;
        this.taskInput = taskInput;

        // Wire button actions
        addButton.setOnAction(e -> addTask());
        completeButton.setOnAction(e -> completeTask());
        deleteButton.setOnAction(e -> deleteTask());
    }

    private void addTask() {
        String text = taskInput.getText().trim();
        if (!text.isEmpty()) {
            service.addTask(text);
            refreshList();
            taskInput.clear();
        }
    }

    private void completeTask() {
        Task selected = taskListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.completeTask(selected.getId());
            refreshList();
        }
    }

    private void deleteTask() {
        Task selected = taskListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Task");
        alert.setHeaderText("Delete selected task?");
        alert.setContentText("Are you sure you want to delete:\n\n" + selected);

        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {
            service.deleteTask(selected.getId());
            refreshList();
        }
    }


    public void refreshList() {
        taskListView.getItems().setAll(service.getTasks());
    }

    public void handleAdd() {
        addTask();
    }

    public void handleComplete() {
        completeTask();
    }

    public void handleDelete() {
        deleteTask();
    }

}
