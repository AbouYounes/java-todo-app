package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Task;
import service.TodoService;

/**
 * JavaFX GUI for the TODO application.
 */
public class GuiTodoApp extends Application {

    private final TodoService service = new TodoService();

    @Override
    public void start(Stage stage) {
        service.loadTasks();

        // UI components
        TextField taskInput = new TextField();
        taskInput.setPromptText("Enter a new task");

        Button addButton = new Button("Add Task");

        ListView<Task> taskListView = new ListView<>();
        taskListView.getItems().addAll(service.getTasks());

        // Button action
        addButton.setOnAction(event -> {
            String text = taskInput.getText().trim();
            if (!text.isEmpty()) {
                service.addTask(text);
                taskListView.getItems().setAll(service.getTasks());
                taskInput.clear();
            }
        });

        VBox root = new VBox(10, taskInput, addButton, taskListView);
        root.setPadding(new javafx.geometry.Insets(10));

        Scene scene = new Scene(root, 400, 500);
        stage.setTitle("TODO App");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(e -> service.saveTasks());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
