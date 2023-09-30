package com.example.kursovaya;

public class Employee {
    private int employeeId ;
    private String name;
    private int userId;

    public Employee(int employeeId, String name) {
        this.name = name;
        this.employeeId = employeeId ;
    }
    public Employee() {
        this.userId = DB.user.getId();
        this.employeeId  = DB.getEmployeeIdByUserId(DB.user.getId()) ;
    }


    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }
}
