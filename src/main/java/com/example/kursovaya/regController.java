package com.example.kursovaya;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class regController {
    @FXML
    private Button RegButton;

    @FXML
    private TextField adressField;

    @FXML
    private Button backButton;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passField;

    @FXML
    private TextField phoneField;


    @FXML
    void initialize() {
        // Регистрация нового пользователя
        RegButton.setOnAction(actionEvent -> {
            signUpNewUser();
        });
        //Кнопка Назад
        backButton.setOnAction(actionEvent -> {
            start.swapScene("signIn.fxml");
        });
    }

    private void signUpNewUser() {
        //Реализация ввода данных в БД
        String name = nameField.getText();
        String password = passField.getText();
        String address = adressField.getText();
        String phone = phoneField.getText();
        //Проверка корректности данных
        if (!name.equals("") && password.length() >=8
                && !address.equals("") && phone.length() == 11){
            //Передача данных в метод класса BD
            DB.registration(name, address, phone, password);
            // Открытие окна приложение при нажатии на кнопку Зарегестрироваться
            start.swapScene("signIn.fxml");
        }
        else
            System.out.println("Введите корректные данные!");
    }
}
