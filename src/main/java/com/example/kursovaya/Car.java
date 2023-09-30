package com.example.kursovaya;

public class Car {
    private String licencePlate;
    private String brand;
    private String model;
    private String color;
    private int mileage;
    private int ownerId;
    private String owner;
    private int employeeId;
    private String part;
    Owner carOwner;


    public Car(String licencePlate, String brand, String model, int ownerId, String color, int mileage) {
        this.licencePlate = licencePlate;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.mileage = mileage;
        this.ownerId = ownerId;
    }



    public Car(String licencePlate, String brand, String model, String owner, int employeeId) {
        this.licencePlate = licencePlate;
        this.brand = brand;
        this.model = model;
        this.owner = owner;
        this.employeeId = employeeId;
    }



    public Car(String licencePlate, String brand, String model, String owner, String color, int mileage, String part) {
        this.licencePlate = licencePlate;
        this.brand = brand;
        this.model = model;
        this.owner = owner;
        this.color = color;
        this.mileage = mileage;
        this.part = part;
    }
    public String getPart() {
        return part;
    }   public Owner getCarOwner() {
        return carOwner;
    }
    public int getEmployeeId() {
        return employeeId;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
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
