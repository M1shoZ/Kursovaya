package com.example.kursovaya;

public class Service {
    private String serviceId;
    private String car;
    private String brand;
    private String model;
    private String color;
    private String mileage;
    private String employeeId;
    private String part;
    private String startTime;
    private String endTime;
    private String worktime;

    public Service(String serviceId, String car, String brand, String model, String color,
                   String mileage, String employeeId, String part, String startTime, String endTime, String worktime) {
        this.serviceId = serviceId;
        this.car = car;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.mileage = mileage;
        this.employeeId = employeeId;
        this.part = part;
        this.startTime = startTime;
        this.endTime = endTime;
        this.worktime = worktime;
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getCar() {
        return car;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getMileage() {
        return mileage;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getPart() {
        return part;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getWorktime() {
        return worktime;
    }
}
