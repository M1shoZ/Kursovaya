package com.example.kursovaya;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class staffAppControler {

    @FXML
    private Button exitButton;

    @FXML
    private Button profileButton;
    @FXML
    void initialize() {
        //Выход из приложения
        exitButton.setOnAction(actionEvent -> {
            start.swapScene("signIn.fxml");
        });
    }
}
