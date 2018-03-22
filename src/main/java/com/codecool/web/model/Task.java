package com.codecool.web.model;

public class Task {

    private String title;
    private String content;
    private Boolean isActive;

    Task (String title, String content) {
        this.title = title;
        this.content = content;
        this.isActive = false;
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
