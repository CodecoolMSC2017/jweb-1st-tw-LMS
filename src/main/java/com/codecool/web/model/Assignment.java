package com.codecool.web.model;

public class Assignment extends Course {

    private String submission;

    public Assignment(String title, String content) {
        super(title, content);
    }

    public Assignment(String title, String content, String submission) {
        super(title, content);
        this.submission = submission;
    }


    public String getSubmission() {
        return submission;
    }

}
