package com.codecool.web.model;

public class Assignment extends Task {

    private String submission;

    public Assignment(String title, String content) {
        super(title, content);
    }


    public String getSubmission() {
        return submission;
    }

    public void setSubmission(String submission) {
        this.submission = submission;
    }
}
