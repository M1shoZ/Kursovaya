package com.example.kursovaya;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Window {

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Registration");

        // Create UI controls
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button registerButton = new Button("Register");
        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Hash the password
            String hashedPassword = Hasher.hashPassword(password);

            // Save the username and hashed password to the database
            DB.saveUser(username, hashedPassword);
        });

        // Add controls to the grid pane
        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameField, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(registerButton, 1, 2);

        Scene scene = new Scene(gridPane, 300, 150);
        stage.setScene(scene);
        stage.show();
    }
}
