package com.example.kursovaya;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class regController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button RegButton;

    @FXML
    private RadioButton adminCheck;

    @FXML
    private TextField loginRegField;

    @FXML
    private PasswordField passRegField;

    @FXML
    void initialize() {
        RegButton.setOnAction(actionEvent -> {
            //Реализация ввода данных в БД
            String username = loginRegField.getText().trim();
            String password = passRegField.getText().trim();
            String role = "";
            if (adminCheck.isSelected())
                role = "Администратор";
            else
                role = "Пользователь";

            // Хэширование пароля
            String hashedPassword = Hasher.hashPassword(password);
            System.out.println(hashedPassword);
            DB.registerUser(username, hashedPassword, role);

            // Открытие окна приложение при нажатии на кнопку Зарегестрироваться
            RegButton.getScene().getWindow().hide();
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


        });
    }

}
