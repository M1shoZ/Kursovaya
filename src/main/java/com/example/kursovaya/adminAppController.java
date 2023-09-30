package com.example.kursovaya;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class adminAppController {

    @FXML
    private Button archiveButton;

    @FXML
    private Button exitButton;

//    @FXML
//    private Button profileButton;

    @FXML
    private Button repairButton;


    @FXML
    private Button addEmployeeButton;

    @FXML
    void initialize() {
        //Переход в окно добавления сотрудника
        addEmployeeButton.setOnAction(actionEvent -> {
            start.swapScene("addEmployee.fxml");
        });
        //Переход в окно назначаения сотрудника на ремонт
        repairButton.setOnAction(actionEvent -> {
            start.swapScene("adminRepair.fxml");
        });
        //Переход в окно архива записей на ремонт
        archiveButton.setOnAction(actionEvent -> {
            start.swapScene("archive.fxml");
        });
        //Выход из приложения
        exitButton.setOnAction(actionEvent -> {
            start.swapScene("signIn.fxml");
        });
    }
}
