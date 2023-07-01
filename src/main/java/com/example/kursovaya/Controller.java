package com.example.kursovaya;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {
    private DB databaseManager;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginButton;

    @FXML
    private TextField loginField;

    @FXML
    private Button loginRegButton;

    @FXML
    private PasswordField passField;

    @FXML
    void initialize() {
        loginButton.setOnAction(actionEvent -> {
            System.out.println("Вы нажали кноку Войти");
//            openWindow("/resources/com.example.kursovaya/app.fxml");

            loginButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("app.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

//            loginButton.getScene().getWindow().hide();


            //Реализация ввода данных в БД
            String username = loginField.getText();
            String password = passField.getText();
            //String role = roleComboBox.getValue();

            // Хэширование пароля
            String hashedPassword = Hasher.hashPassword(password);
            System.out.println(hashedPassword);
            // Save the username and hashed password to the database
            //DB.registerUser(username, hashedPassword, role);
            DB.registerUser(username, hashedPassword);
        });
    }
}


