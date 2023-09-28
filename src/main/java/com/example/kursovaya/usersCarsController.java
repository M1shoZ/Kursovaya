package com.example.kursovaya;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class usersCarsController {
    @FXML
    private Button addCarButton;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Car, String> brandColumn;

    @FXML
    private TextField brandField;

    @FXML
    private TableView<Car> carsTable;

    @FXML
    private TableColumn<Car, String> colorColumn;

    @FXML
    private TextField colorField;

    @FXML
    private TableColumn<Car, String> licensePlateColumn;

    @FXML
    private TextField licensePlateField;

    @FXML
    private TableColumn<Car, Integer> mileageColumn;

    @FXML
    private TextField mileageField;

    @FXML
    private TableColumn<Car, String> modelColumn;

    @FXML
    private TextField modelField;
    ObservableList<Car> cars = FXCollections.observableArrayList();
    Owner carOwner = DB.owner;

    @FXML
    void initialize() {
        System.out.println(carOwner.getId());
        //Вывести таблицу с данными
        setTable();
        //Кнопка Назад
        backButton.setOnAction(actionEvent -> {
            start.swapScene("ownerApp.fxml");
        });
        //Добавление авто
        addCarButton.setOnAction(actionEvent -> {
            addCar();
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

    private void addCar() {
        String model = modelField.getText();
        String brand = brandField.getText();
        String color = colorField.getText();
        String licensePlate = licensePlateField.getText();
        String  mileage = mileageField.getText();
        if (!model.equals("") && !brand.equals("") && !color.equals("")
                && !licensePlate.equals("") && !mileage.equals("")){
            DB.addCar(licensePlate, brand, model, color, mileage, carOwner.getId());
            setTable();
        }
    }
}
