package com.codecool.web.model;

public class Task {

    private String title;
    private String content;
    private Boolean isActive;

    Task (String title, String content, Boolean isActive) {
        this.title = title;
        this.content = content;
        this.isActive = isActive;
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
}
