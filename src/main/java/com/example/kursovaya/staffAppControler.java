package com.example.kursovaya;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class staffAppControler {

    @FXML
    private Button addPartButton;
    @FXML
    private Button exitButton;

    @FXML
    private TableColumn<Car, String> brandColumn;

    @FXML
    private TableView<Car> employeeCarTable;

    @FXML
    private TableColumn<Car, String> colorColumn;


    @FXML
    private TableColumn<Part, String> partColumn;

    @FXML
    private TableColumn<Car, String> licensePlateColumn;

    @FXML
    private TableColumn<Car, Integer> mileageColumn;

    @FXML
    private TableColumn<Car, String> modelColumn;

    @FXML
    private TableColumn<Car, String> ownerColumn;

    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn<Part, Integer> serialNumberColumn;

    @FXML
    private TextField serialNumberField;
    @FXML
    private TableColumn<Car, String> installedPartColumn;

    ObservableList<Car> employeeCar = FXCollections.observableArrayList();
    ObservableList<Part> matchingParts = FXCollections.observableArrayList();

    Car car = DB.car;
    User user = DB.user;

    @FXML
    void initialize() {
        //Добавить деталь
        addPartButton.setOnAction(actionEvent -> {
            addPart();
        });
        //Вывести таблицу с авто записаными на ремонт
        setEmployeeCarTable();
        //Вывод таблицы с подходящими деталями
        setPartsTable();
        //Выход из приложения
        exitButton.setOnAction(actionEvent -> {
            start.swapScene("signIn.fxml");
        });
    }

    private void addPart() {
        String serialNumber = serialNumberField.getText();
        if (!serialNumber.equals("")){
            DB.setUpPart(serialNumber, licensePlateColumn.getCellData(0));
            DB.stopCountdown(licensePlateColumn.getCellData(0));
            DB.calculateTime(licensePlateColumn.getCellData(0));
            setEmployeeCarTable();
        }
    }

    private void setPartsTable() {
        System.out.println(modelColumn.getCellData(0));
        serialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        partColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        partsTable.setItems(matchingParts);
        partsTable.getItems().clear();
        matchingParts.addAll(DB.getMatchingParts(modelColumn.getCellData(0)));
        partsTable.refresh();
    }

    private void setEmployeeCarTable() {
        licensePlateColumn.setCellValueFactory(new PropertyValueFactory<>("licencePlate"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        mileageColumn.setCellValueFactory(new PropertyValueFactory<>("mileage"));
        ownerColumn.setCellValueFactory(new PropertyValueFactory<>("owner"));
        installedPartColumn.setCellValueFactory(new PropertyValueFactory<>("part"));
        employeeCarTable.setItems(employeeCar);
        employeeCarTable.getItems().clear();
        employeeCar.addAll(DB.getEmployeeServiceCars(DB.getEmployeeIdByUserId(user.getId())));
        employeeCarTable.refresh();
    }
}
