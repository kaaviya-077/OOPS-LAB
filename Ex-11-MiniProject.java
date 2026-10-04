CREATE DATABASE studentdb;

USE studentdb;

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    course VARCHAR(100) NOT NULL
);
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentManagementApp extends Application {

    private Connection connection;

    private final TextField idField = new TextField();
    private final TextField nameField = new TextField();
    private final TextField ageField = new TextField();
    private final TextField courseField = new TextField();
    private final TextArea displayArea = new TextArea();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        connectToDatabase();

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Font font = new Font("Arial", 14);

        idField.setPromptText("ID (for Update/Delete)");
        idField.setFont(font);

        nameField.setPromptText("Name");
        nameField.setFont(font);

        ageField.setPromptText("Age");
        ageField.setFont(font);

        courseField.setPromptText("Course");
        courseField.setFont(font);

        Button createButton = new Button("Create");
        createButton.setFont(font);
        createButton.setStyle(
                "-fx-background-color: #4CAF50; -fx-text-fill: white;"
        );
        createButton.setOnAction(event -> createStudent());

        Button readButton = new Button("Display");
        readButton.setFont(font);
        readButton.setStyle(
                "-fx-background-color: #2196F3; -fx-text-fill: white;"
        );
        readButton.setOnAction(event -> readStudents());

        Button updateButton = new Button("Update");
        updateButton.setFont(font);
        updateButton.setStyle(
                "-fx-background-color: #FF9800; -fx-text-fill: white;"
        );
        updateButton.setOnAction(event -> updateStudent());

        Button deleteButton = new Button("Delete");
        deleteButton.setFont(font);
        deleteButton.setStyle(
                "-fx-background-color: #F44336; -fx-text-fill: white;"
        );
        deleteButton.setOnAction(event -> deleteStudent());

        displayArea.setFont(font);
        displayArea.setEditable(false);
        displayArea.setWrapText(true);

        gridPane.add(new Label("ID:"), 0, 0);
        gridPane.add(idField, 1, 0);

        gridPane.add(new Label("Name:"), 0, 1);
        gridPane.add(nameField, 1, 1);

        gridPane.add(new Label("Age:"), 0, 2);
        gridPane.add(ageField, 1, 2);

        gridPane.add(new Label("Course:"), 0, 3);
        gridPane.add(courseField, 1, 3);

        gridPane.add(createButton, 0, 4);
        gridPane.add(readButton, 1, 4);

        gridPane.add(updateButton, 0, 5);
        gridPane.add(deleteButton, 1, 5);

        GridPane.setMargin(createButton, new Insets(5));
        GridPane.setMargin(readButton, new Insets(5));
        GridPane.setMargin(updateButton, new Insets(5));
        GridPane.setMargin(deleteButton, new Insets(5));

        gridPane.add(displayArea, 0, 6, 2, 1);

        Scene scene = new Scene(gridPane, 400, 500);

        primaryStage.setTitle("Student Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/studentdb",
                    "root",
                    "1234"
            );

            System.out.println("Database connection successful");

        } catch (ClassNotFoundException exception) {
            System.err.println("MySQL JDBC Driver not found");
            exception.printStackTrace();

        } catch (SQLException exception) {
            System.err.println("Database connection failed");
            exception.printStackTrace();
        }
    }

    private void createStudent() {
        try {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String course = courseField.getText();

            String sql = """
                    INSERT INTO students (name, age, course)
                    VALUES (?, ?, ?)
                    """;

            try (PreparedStatement statement =
                         connection.prepareStatement(sql)) {

                statement.setString(1, name);
                statement.setInt(2, age);
                statement.setString(3, course);

                statement.executeUpdate();
                displayArea.setText("Student created successfully.");
            }

        } catch (NumberFormatException exception) {
            displayArea.setText("Age must be a valid number.");

        } catch (SQLException exception) {
            displayArea.setText("Error creating student.");
            exception.printStackTrace();
        }
    }

    private void readStudents() {
        String sql = "SELECT * FROM students";

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            StringBuilder students = new StringBuilder();

            while (resultSet.next()) {
                students.append("ID: ")
                        .append(resultSet.getInt("id"))
                        .append(", Name: ")
                        .append(resultSet.getString("name"))
                        .append(", Age: ")
                        .append(resultSet.getInt("age"))
                        .append(", Course: ")
                        .append(resultSet.getString("course"))
                        .append("
");
            }

            displayArea.setText(students.toString());

        } catch (SQLException exception) {
            displayArea.setText("Error reading students.");
            exception.printStackTrace();
        }
    }

    private void updateStudent() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String course = courseField.getText();

            String sql = """
                    UPDATE students
                    SET name = ?, age = ?, course = ?
                    WHERE id = ?
                    """;

            try (PreparedStatement statement =
                         connection.prepareStatement(sql)) {

                statement.setString(1, name);
                statement.setInt(2, age);
                statement.setString(3, course);
                statement.setInt(4, id);

                int rowsUpdated = statement.executeUpdate();

                if (rowsUpdated > 0) {
                    displayArea.setText(
                            "Student updated successfully."
                    );
                } else {
                    displayArea.setText(
                            "No student found with that ID."
                    );
                }
            }

        } catch (NumberFormatException exception) {
            displayArea.setText(
                    "ID and age must be valid numbers."
            );

        } catch (SQLException exception) {
            displayArea.setText("Error updating student.");
            exception.printStackTrace();
        }
    }

    private void deleteStudent() {
        try {
            int id = Integer.parseInt(idField.getText());

            String sql = "DELETE FROM students WHERE id = ?";

            try (PreparedStatement statement =
                         connection.prepareStatement(sql)) {

                statement.setInt(1, id);

                int rowsDeleted = statement.executeUpdate();

                if (rowsDeleted > 0) {
                    displayArea.setText(
                            "Student deleted successfully."
                    );
                } else {
                    displayArea.setText(
                            "No student found with that ID."
                    );
                }
            }

        } catch (NumberFormatException exception) {
            displayArea.setText("ID must be a valid number.");

        } catch (SQLException exception) {
            displayArea.setText("Error deleting student.");
            exception.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }

        super.stop();
    }
}
