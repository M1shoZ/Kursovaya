package com.example.kursovaya;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class archiveController {

    @FXML
    private TableView<Service> archiveTable;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Service, String> brandColumn;

    @FXML
    private TableColumn<Service, String> colorColumn;

    @FXML
    private TableColumn<Service, String> employeeIdColumn;

    @FXML
    private TableColumn<Service, String> endDateColumn;

    @FXML
    private TableColumn<Service, String> licensePlateColumn;

    @FXML
    private TableColumn<Service, Integer> mileageColumn;

    @FXML
    private TableColumn<Service, String> modelColumn;

    @FXML
    private TableColumn<Service, String> installedPartColumn;

    @FXML
    private TableColumn<Service, String> serviceIdColumn;

    @FXML
    private TableColumn<Service, String> startDateColumn;

    @FXML
    private TableColumn<Service, String> timeColumn;

    ObservableList<Service> s = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        //Вывод сводной таблицы
        setTable();
        //Выход из окна
        backButton.setOnAction(actionEvent -> {
            start.swapScene("adminApp.fxml");
        });
    }

    private void setTable() {
        licensePlateColumn.setCellValueFactory(new PropertyValueFactory<>("car"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        mileageColumn.setCellValueFactory(new PropertyValueFactory<>("mileage"));
        installedPartColumn.setCellValueFactory(new PropertyValueFactory<>("part"));
        serviceIdColumn.setCellValueFactory(new PropertyValueFactory<>("serviceId"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("worktime"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        archiveTable.setItems(s);
        archiveTable.getItems().clear();
        s.addAll(DB.getAllService());
        archiveTable.refresh();
    }


}
