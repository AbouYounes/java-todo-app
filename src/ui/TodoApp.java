package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Task;
import service.TodoService;

/**
 * JavaFX View & application entry point.
 */
public class TodoApp extends Application {

    private final TodoService service = new TodoService();

    @Override
    public void start(Stage stage) {
        service.loadTasks();

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

        // Disable buttons when nothing is selected
        completeButton.disableProperty()
                .bind(taskListView.getSelectionModel().selectedItemProperty().isNull());
        deleteButton.disableProperty()
                .bind(taskListView.getSelectionModel().selectedItemProperty().isNull());

        // Controller
        TodoController controller = new TodoController(
                service,
                taskListView,
                taskInput,
                addButton,
                completeButton,
                deleteButton
        );

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

        stage.setOnCloseRequest(e -> service.saveTasks());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
