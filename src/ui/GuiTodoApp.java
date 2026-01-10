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
        addButton.setId("add");

        Button completeButton = new Button("Complete Selected");
        completeButton.setId("complete");

        Button deleteButton = new Button("Delete Selected");
        deleteButton.setId("delete");


        ListView<Task> taskListView = new ListView<>();
        taskListView.getItems().addAll(service.getTasks());

        // Disable action buttons when nothing is selected
        completeButton.disableProperty()
                .bind(taskListView.getSelectionModel().selectedItemProperty().isNull());
        deleteButton.disableProperty()
                .bind(taskListView.getSelectionModel().selectedItemProperty().isNull());

        // Add task
        addButton.setOnAction(event -> {
            String text = taskInput.getText().trim();
            if (!text.isEmpty()) {
                service.addTask(text);
                taskListView.getItems().setAll(service.getTasks());
                taskInput.clear();
            }
        });

        // Complete selected task
        completeButton.setOnAction(event -> {
            Task selected = taskListView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                service.completeTask(selected.getId());
                taskListView.getItems().setAll(service.getTasks());
            }
        });

        // Delete selected task
        deleteButton.setOnAction(event -> {
            Task selected = taskListView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                service.deleteTask(selected.getId());
                taskListView.getItems().setAll(service.getTasks());
            }
        });

        VBox root = new VBox(
                10,
                taskInput,
                addButton,
                completeButton,
                deleteButton,
                taskListView
        );
        root.setPadding(new javafx.geometry.Insets(10));

        Scene scene = new Scene(root, 400, 550);
        scene.getStylesheets().add(
                getClass().getResource("style.css").toExternalForm()
        );

        stage.setTitle("TODO App");
        stage.setScene(scene);
        stage.show();

        // Persist on close
        stage.setOnCloseRequest(e -> service.saveTasks());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
