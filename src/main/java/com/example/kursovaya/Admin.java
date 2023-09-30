package com.example.kursovaya;

public class Admin {
    private int id;
    private int userId;

    public Admin() {
        this.id = DB.getAdminId(DB.user.getId());
        this.userId = DB.user.getId();
    }

    public Admin(int id, int userId) {
        this.id = id;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

}
