package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Task;
import service.TodoService;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;


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
                getClass().getResource("/ui/style.css").toExternalForm()
        );

        // Keyboard shortcuts
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                controller.handleAdd();
            } else if (event.getCode() == KeyCode.DELETE) {
                controller.handleDelete();
            }
        });

        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN),
                controller::handleComplete
        );


        stage.setTitle("TODO App");
        stage.setScene(scene);
        stage.show();
        taskInput.requestFocus();


        stage.setOnCloseRequest(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit Application");
            alert.setHeaderText("Exit TODO App?");
            alert.setContentText("Do you really want to exit?");

            if (alert.showAndWait().orElse(ButtonType.CANCEL) != ButtonType.OK) {
                e.consume(); // cancel close
            } else {
                service.saveTasks();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
