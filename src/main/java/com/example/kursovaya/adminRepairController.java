package com.example.kursovaya;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class adminRepairController {

    @FXML
    private TableColumn<Car, Integer> assignedEmployeeIdColumn;
    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Car, String> brandColumn;

    @FXML
    private TableView<Car> carsToRepairTable;

    @FXML
    private TableColumn<Employee, String> employeeColumn;

    @FXML
    private TableColumn<Employee, String> employeeIdColumn;

    @FXML
    private TextField employeeIdField;

    @FXML
    private Button employeeToRepairButton;

    @FXML
    private TableColumn<Car, String> licensePlateColumn;

    @FXML
    private TextField licensePlateField;

    @FXML
    private TableColumn<Car, String> modelColumn;


    @FXML
    private TableColumn<Car, String> ownerColumn;

    @FXML
    private TableView<Employee> staffTable;

    ObservableList<Car> serviceCars = FXCollections.observableArrayList();
    ObservableList<Employee> Staff = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        //Назначить сотрудника
        employeeToRepairButton.setOnAction(actionEvent -> {
            AssignEmployee();
        });
        //Вывести таблицу с авто записаными на ремонт
        setCarsToRepairTable();
        //Вывести таблицу с сотудниками
        setStaffTable();
        //Выход из окна
        backButton.setOnAction(actionEvent -> {
            start.swapScene("adminApp.fxml");
        });
    }

    private void AssignEmployee() {
        int employeeId = Integer.parseInt(employeeIdField.getText());
        String licensePlate = licensePlateField.getText();
        if (!licensePlate.equals("")){
            DB.assignEmployee(employeeId, licensePlate);
            DB.startCountdown(licensePlate);
        }
        setCarsToRepairTable();
    }

    private void setCarsToRepairTable() {
        assignedEmployeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        ownerColumn.setCellValueFactory(new PropertyValueFactory<>("owner"));
        licensePlateColumn.setCellValueFactory(new PropertyValueFactory<>("licencePlate"));
        carsToRepairTable.setItems(serviceCars);
        carsToRepairTable.getItems().clear();
        serviceCars.addAll(DB.getAllServiceCars());
        carsToRepairTable.refresh();
    }

    private void setStaffTable() {
        employeeColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        staffTable.setItems(Staff);
        staffTable.getItems().clear();
        Staff.addAll(DB.getStaff());
        staffTable.refresh();
    }
}
