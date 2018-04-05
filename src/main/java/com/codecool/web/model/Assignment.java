package com.codecool.web.model;

public class Assignment extends Course {

    private String submission;

    public Assignment(String name, String desc) {
        super(name, desc);
    }

    public Assignment(int id,String name, String desc, boolean isActive,int assignId , String submission) {
        super(id,name, desc,isActive);
        this.submission = submission;
    }


    public String getSubmission() {
        return submission;
    }

    /*public void setSubmission() {

    }*/



    public boolean isActive() {
        return isActive;
    }
}
