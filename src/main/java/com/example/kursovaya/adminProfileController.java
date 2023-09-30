package com.example.kursovaya;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class adminProfileController {
    @FXML
    private TextField phoneField;
    @FXML
    private TextField nameField;

    @FXML
    private Button changeDataButton;

    @FXML
    private TextField addressField;

    @FXML
    private TableView<User> profileTable;
    @FXML
    private TableColumn<User, String> addressColumn;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, String> phoneColumn;

    @FXML
    private TextField passwordField;
    User user = DB.user;

    @FXML
    void initialize() {
        //Вывести таблицу с данными
        setTable();
        //Кнопка Назад
        backButton.setOnAction(actionEvent -> {
            start.swapScene("adminApp.fxml");
        });
        changeDataButton.setOnAction(actionEvent -> {
            editName();
            editPhone();
            editAddress();
            editPassword();
        });
    }
    //Вывести таблицу с данными
    private void setTable() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        profileTable.getItems().add(user);
    }
    private void editName() {
        String newName = nameField.getText();
        if (!newName.equals("")){
            DB.updateName(newName, user.getId());
            profileTable.refresh();
        }else
            System.out.println("Nothing to change" + user.getId());
    }
    private void editPassword() {
        String newPassword = passwordField.getText();
        if (newPassword.length() >= 8){
            DB.updatePassword(newPassword, user.getId());
        }else
            System.out.println("Пароль должен быть не менее 8 символов");
    }

    private void editAddress() {
        String newAddress = addressField.getText();
        if (!newAddress.equals("")){
            DB.updateAddress(newAddress, user.getId());
            profileTable.refresh();
        }
    }

    private void editPhone() {
        String newPhone = phoneField.getText();
        if (newPhone.length() == 11){
            DB.updatePhone(newPhone, user.getId());
            profileTable.refresh();
        }
    }
}
