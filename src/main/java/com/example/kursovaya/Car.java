package com.example.kursovaya;

public class Car {
    private String licencePlate;
    private String brand;
    private String model;
    private String color;
    private int mileage;
    private int ownerId;
    Owner carOwner;


    public Car(String licencePlate, String brand, String model, String color, int mileage, int ownerId) {
        this.licencePlate = licencePlate;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.mileage = mileage;
        this.ownerId = ownerId;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
