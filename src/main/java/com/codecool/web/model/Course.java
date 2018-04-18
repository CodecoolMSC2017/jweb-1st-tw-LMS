package com.codecool.web.model;

import java.util.Random;

public class Course {
    private int id;
    private String name;
    private String desc;
    boolean isActive;

    public Course(String name, String desc) {
        this.id = id;
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


    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }


    public boolean getIsActive() {
        return isActive;
    }

    public void setActivity() {
        if (getIsActive()) {
            isActive = false;
        } else {
            isActive = true;
        }
    }

}
