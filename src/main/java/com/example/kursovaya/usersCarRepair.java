package com.example.kursovaya;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class usersCarRepair {

    @FXML
    private Button signUpCarButton;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Car, String> brandColumn;


    @FXML
    private TableView<Car> carsTable;

    @FXML
    private TableColumn<Car, String> colorColumn;

    @FXML
    private TableColumn<Car, String> licensePlateColumn;

    @FXML
    private TextField licensePlateField;

    @FXML
    private TableColumn<Car, Integer> mileageColumn;

    @FXML
    private TableColumn<Car, String> modelColumn;

    ObservableList<Car> cars = FXCollections.observableArrayList();
    Owner carOwner = DB.owner;

    @FXML
    void initialize() {
        //Вывести таблицу с данными
        setTable();
        //Записать автомобиль на ремонт
        signUpCarButton.setOnAction(actionEvent -> {
            signUpCar();
        });
        //Кнопка Назад
        backButton.setOnAction(actionEvent -> {
            start.swapScene("ownerApp.fxml");
        });
    }

    private void setTable() {
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        licensePlateColumn.setCellValueFactory(new PropertyValueFactory<>("licencePlate"));
        mileageColumn.setCellValueFactory(new PropertyValueFactory<>("mileage"));
        carsTable.setItems(cars);
        carsTable.getItems().clear();
        cars.addAll(DB.getCars(carOwner.getId()));
        carsTable.refresh();
    }

    private void signUpCar() {
        String licensePlate = licensePlateField.getText();
        if (!licensePlate.equals("")){
            DB.signUpCar(licensePlate);
        }
    }

}
