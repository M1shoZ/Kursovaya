package com.example.kursovaya;


public class Owner {
    private int id;
    private int userId;

    public Owner() {
        this.id = DB.getOwnerId(DB.user.getId());
        this.userId = DB.user.getId();
    }

    public Owner(int id, int userId) {
        this.id = id;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }
}
