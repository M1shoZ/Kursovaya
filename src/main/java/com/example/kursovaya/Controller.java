package com.example.kursovaya;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            // Авторизация пользователя
            String loginText = loginField.getText().trim();
            String passText = passField.getText().trim();

            if (!loginText.equals("") && !passText.equals("")){
                loginUser(loginText, passText);

                // Открытие окна основного приложения после нажатия кнопки Войти
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
            }
            else
                System.out.println("Заполните все поля");

        });
        //Открытие окна регистрации после нажатия кнопки Зарегистрироваться
        loginRegButton.setOnAction(actionEvent -> {
            loginRegButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("regWindow.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

    //Авторизация пользователя
    public static void loginUser(String loginText, String passText) {
        DB db = new DB();
        String loginField = loginText;
        String passField = passText;
        ResultSet resultSet = db.getUser(loginText, passText);

        int k = 0;
        while (true){
            try {
                if (resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            k++;
        }
        if (k >= 1){
            System.out.println("Success!");
        }
    }
}


