package com.example.kursovaya;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Window  {

    private DB databaseManager;


    public static void show() {
        Stage stage = new Stage();
        stage.setTitle("Registration");

        // Создание элементов управления
        Label usernameLabel = new Label("Имя пользователя:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Пароль:");
        PasswordField passwordField = new PasswordField();

        Label roleLabel = new Label("Роль:");
        ComboBox<String> roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("Пользователь", "Администратор");
        roleComboBox.setValue("Пользователь");

        Button registerButton = new Button("Зарегистрироваться");

        // Обработчик события нажатия на кнопку "Register"
        registerButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String role = roleComboBox.getValue();

            // Хэширование пароля
            String hashedPassword = Hasher.hashPassword(password);

            // Save the username and hashed password to the database
            DB.registerUser(username, hashedPassword, role);
        });

        // Создание сетки и добавление элементов управления
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(5);
        gridPane.setHgap(10);
        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameField, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(roleLabel, 0, 2);
        gridPane.add(roleComboBox, 1, 2);
        gridPane.add(registerButton, 1, 3);

        // Создание сцены и установка на ней сетки
        Scene scene = new Scene(gridPane, 300, 150);
        stage.setScene(scene);
        stage.show();
    }
}
