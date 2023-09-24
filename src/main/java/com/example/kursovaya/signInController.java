package com.example.kursovaya;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
public class signInController {
    public static boolean validation = false;
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
            String name = loginField.getText().trim();
            String password = passField.getText().trim();
            if (!name.equals("") && !password.equals("")){
                DB.loginUser(name, password);
                // Открытие окна основного приложения после нажатия кнопки Войти
                // при верных данных
                if (validation){
                    start.swapScene("app.fxml");
                }
                else
                    System.out.println("Произошла ошибка!");
            }
        });

        //Открытие окна регистрации после нажатия кнопки Зарегистрироваться
        loginRegButton.setOnAction(actionEvent -> {
            start.swapScene("regWindow.fxml");
        });
    }
}


