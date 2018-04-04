package com.codecool.web.service;

import com.codecool.web.model.Course;

import java.util.List;

public interface CourseService {

    List<Course> getCourses();

    void addNewCourse(String title, String description);

    Course getCourse(int courseid);

    List<Course> availableCourses();

    void removeCourse(int id);
}


