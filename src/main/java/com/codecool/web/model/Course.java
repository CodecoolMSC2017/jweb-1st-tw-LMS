package com.codecool.web.model;

import java.util.Random;

public class Course {
    private int id;
    private String name;
    private String desc;
    boolean isActive;

    public Course(String name, String desc) {
        this.id = generateId();
        this.name = name;
        this.desc = desc;
        this.isActive = false;
    }

    public Course(int id,String name, String desc,boolean isActive) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public int generateId() {
        return new Random().nextInt(100000)+1;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }


    public boolean getActive() {
        return isActive;
    }

    public void setActivity() {
        if (getActive()) {
            isActive = false;
        } else {
            isActive = true;
        }
    }

}
