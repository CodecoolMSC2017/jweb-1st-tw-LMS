package com.codecool.web.model;

public class Assignment extends Course {

    private String submission;
    private int maxPoint;
    private int pointsGot;

    public Assignment(String name, String desc, int maxPoint) {
        super(name, desc);
        this.maxPoint = maxPoint;
        this.pointsGot = 0;
    }

    public Assignment(int id, String name, String desc,int maxPoint, boolean isActive, int assignId, String submission) {
        super(id, name, desc, isActive);
        this.maxPoint = maxPoint;
        this.submission = submission;
    }


    public String getSubmission() {
        return submission;
    }

    public void setSubmission(String submission) {
        this.submission = submission;
    }


    public boolean isActive() {
        return isActive;
    }

    public int getMaxPoints() {
        return maxPoint;
    }

    public int getPointsGot() {
        return pointsGot;
    }

    public void setPointsGot(int points) {
        if (maxPoint < points) {
            pointsGot = maxPoint;
        } else {
            pointsGot = points;
        }
    }
}
