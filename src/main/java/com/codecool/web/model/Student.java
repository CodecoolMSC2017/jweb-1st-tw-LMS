package com.codecool.web.model;

public class Student extends User {

    private int points;

    Student(int id, String name, String email, String password, boolean permission, int points) {
        super(id, name, email, password, permission);
        this.points = points;
    }

    public int getPoints() {
        return points;
    }
}
