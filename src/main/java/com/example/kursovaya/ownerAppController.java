package com.example.kursovaya;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ownerAppController {
    @FXML
    private Button exitButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button usersCarButton;

    @FXML
    private Button repairButton;

    @FXML
    void initialize() {
        //Переход к окну профиля
        profileButton.setOnAction(actionEvent -> {
            start.swapScene("profile.fxml");
        });
        //Переход к окну "Мои авто"
        usersCarButton.setOnAction(actionEvent -> {
            start.swapScene("usersCars.fxml");
        });
        //Выход из приложения
        exitButton.setOnAction(actionEvent -> {
            start.swapScene("signIn.fxml");
        });
        //Запись на ремонт
        repairButton.setOnAction(actionEvent -> {
            start.swapScene("usersCarRepair.fxml");
        });
    }

}
