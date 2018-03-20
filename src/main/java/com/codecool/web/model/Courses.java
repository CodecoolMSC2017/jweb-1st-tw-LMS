package com.codecool.web.model;

import java.util.List;

public class Courses {
    private List<Course> courseList;

    public Courses(List<Course> courseList) {
        this.courseList = courseList;
    }

    public List<Course> getCourseList() {
        return courseList;
    }
}
