package com.codecool.web.model;

import java.util.Random;

public class Task {

    private int id;
    private String title;
    private String content;
    private Boolean isActive;

    public Task (String title, String content) {
        this.id = generateId();
        this.title = title;
        this.content = content;
        this.isActive = false;
    }

    public int getId() {
        return id;
    }

    public int generateId() {
        Random rand = new Random();
        return rand.nextInt(10000);
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActivity() {
        if(getActive()) {
            isActive = false;
        }
        isActive = true;
    }


}
