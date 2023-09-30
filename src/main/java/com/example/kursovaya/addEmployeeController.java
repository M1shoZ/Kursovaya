package com.example.kursovaya;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class addEmployeeController {
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
        //Добавить сотрудника
        RegButton.setOnAction(actionEvent -> {
            addEmployee();
        });
        //Выход из окна
        backButton.setOnAction(actionEvent -> {
            start.swapScene("adminApp.fxml");
        });
    }

    private void addEmployee() {
        String name = nameField.getText();
        String password = passField.getText();
        String address = adressField.getText();
        String phone = phoneField.getText();
        //Проверка корректности данных
        if (!name.equals("") && password.length() >=8
                && !address.equals("") && phone.length() == 11){
            //Передача данных в метод класса BD
            DB.addEmployee(name, address, phone, password);
        }
        else
            System.out.println("Введите корректные данные!");
    }
}
