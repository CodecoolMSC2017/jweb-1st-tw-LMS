package com.codecool.web.model;

public class Assignment extends Course {
    private int assignId;
    private String submission;

    public Assignment(String name, String desc) {
        super(name, desc);
        this.assignId = generateId();

    }

    public Assignment(int id ,String name, String desc, boolean isActive, String submission) {
        super(id, name, desc, isActive);
        this.submission = null;
    }


    public String getSubmission() {
        return submission;
    }

    /*public void setSubmission() {

    }*/


    public int getAssignId() {
        return assignId;
    }
}
