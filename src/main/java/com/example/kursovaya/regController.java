package com.example.kursovaya;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class regController {
    public static boolean validation = false;
    @FXML
    private Button RegButton;

    @FXML
    private RadioButton adminCheck;

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
        RegButton.setOnAction(actionEvent -> {
            //Реализация ввода данных в БД
            String name = nameField.getText().trim();
            String password = passField.getText().trim();
            String address = adressField.getText().trim();
            String phone_number = phoneField.getText().trim();
            String role = "";
            if (adminCheck.isSelected())
                role = "Администратор";
            else
                role = "Пользователь";
            //Передача данных в метод класса BD
            if (!name.equals("") && password.length() >=8
            && !address.equals("") && !phone_number.equals("")){
                DB.registration(name, address, phone_number, password, role);
                // Открытие окна приложение при нажатии на кнопку Зарегестрироваться
                start.swapScene("app.fxml");
            }
            else
                System.out.println("Введите данные!");
        });
        //Кнопка Назад
        backButton.setOnAction(actionEvent -> {
            start.swapScene("signIn.fxml");
        });
    }

}
