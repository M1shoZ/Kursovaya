package com.example.kursovaya;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class adminAppController {

    @FXML
    private Button exitButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button repairButton;

    @FXML
    void initialize() {
        //Переход в окно назначаения сотрудника на ремонт
        //Выход из приложения
        exitButton.setOnAction(actionEvent -> {
            start.swapScene("signIn.fxml");
        });
    }
}
