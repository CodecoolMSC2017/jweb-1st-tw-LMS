package com.codecool.web.model;

import java.util.List;

public class Courses {
    private List<Course> coursesList;

    public Courses(List<Course> coursesList) {
        this.coursesList = coursesList;
    }

    public List<Course> getCourseList() {
        return coursesList;
    }
}
