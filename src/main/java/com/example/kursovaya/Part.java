package com.example.kursovaya;

public class Part {
    private int serialNumber;
    private String category;

    public Part(int serialNumber, String category) {
        this.serialNumber = serialNumber;
        this.category = category;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
